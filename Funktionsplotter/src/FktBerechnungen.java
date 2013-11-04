import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class FktBerechnungen {
	
	// Pr�fung auf Art der Zeichen
	
	// TODO falls Zeit: try catch zur Ablaufsteuerung entfernen, Verrechnungen entfernen/ Vereinfachen?
	// Pr�fung auf Zeichenreihenfolge (doppelte Rechenzeichen oder == )
	public boolean doppelteZeichen(String funktion)
	{
		if (funktion.length()==0 || funktion.length()==1)
			return true;
		
		for(int i=0;i<=funktion.length();i++)
		{
			char test;
			char next;
			// Wenn Exeption auftritt kann abgebrochen werden, da keine Zeichen mehr vorhanden sind
			try
			{
			 test =funktion.charAt(i);
			 next =funktion.charAt(i+1);
			}
			
			catch(StringIndexOutOfBoundsException e)
			{
				return true;
			}
			
			if (test=='+' || test=='-' || test=='*' || test=='/' || test=='^'  )
			{
				if (test=='^' && next=='-' || test=='^' && next=='+')
				{
					
				}	
				else
				
				{
					// doppelte Rechenzeichen und auch zB -=
					if (next=='+' || next=='-' || next=='*' || next=='/' || next=='^' || next=='=')
					{
						return false;
					}
				
				}
				
			}
			//Spezialfall == 
			if(test=='=' && next=='=' || test=='=' && next=='*'|| test=='=' && next=='/' || test=='/' && next=='=' || test=='*' && next=='=' )
				return false;
			
			
		}
		
		return true;
	}
	
	public boolean klammernPruefen(String funktion)
	{
		ParenMatcher pm = new ParenMatcher();
		pm.setInput(funktion);
		return(pm.parenMatch());
	}

	// TODO falls Zeit: try catch zur Ablaufsteuerung entfernen
	public boolean variablenPruefung(String funktion)
	{
		for(int i=0;i<funktion.length();i++)
		{
			char test;
			char next;
			try
			{
			test=funktion.charAt(i);
			next=funktion.charAt(i+1);
			}
			catch(StringIndexOutOfBoundsException e)
			{
				// Funktion zu Ende
				return true;
			}
			
			if (Character.isLetter(test))
				if (Character.isLetter(next))
				{
					return false;
				}
				
		}
		return true;
	}
	
	// entfernt alle Sonderzeichen und Leerzeichen (zB. Backslash etc.)
	public void entferneSonstigeZeichen(String funktion)
	{
		char test;
		for(int i=0;i<=funktion.length()-1;i++)
		{
			test=funktion.charAt(i);
	    
			if(!(Character.isLetterOrDigit(test) || test=='+' || test=='-' || test=='*' || test=='/'))
			{
	    	funktion.replace(test, ' ');
			}
	    
		}
		
		funktion.replaceAll("\\s", "");
	}
	
	// Erstellt Tokens aus dem Eingabestring
	public List<Token> tokenize(String funktion)
	{	
		// Maps die alle Operationen,Konstanten enthalten
		 Map<String,Operation> rechenoperationen = new HashMap<String, Operation>();
	     Map<String, ZahlToken> konstanten = new HashMap<String, ZahlToken>();
	     List<Map<String, ? extends Token>> maps = new ArrayList<Map<String, ? extends Token>>();
	     
	     // Operationsliste fuellen
	     for(Operation op:Operation.values())
	     {
	    	 rechenoperationen.put(op.getName(), op);
	     }
	     
	     // Einfuegen von Konstanten
	     konstanten.put("PI", new ZahlToken(Math.PI));
	     konstanten.put("E", new ZahlToken(Math.E));
	     
	     // Liste der Maps fuellen
	     maps.add(rechenoperationen);
	     maps.add(konstanten);
		
		//Ergebnisliste
		List<Token> liste= new ArrayList<Token>();
		
		// aktuelle Position
		int aktuellePos=0;
		
		
		//Schleife �ber alle Zeichen des Eingabestrings
		while(aktuellePos<funktion.length())
		{
			// aktueller Buchstabe
			char aktuell= funktion.charAt(aktuellePos);
			
			// Hinzuf�gen einen Tokens f�r die Variable (x)
			if(aktuell=='x')
			{
				liste.add(new XToken());
				aktuellePos++;
				continue;
			}
			
			if (Character.isDigit(aktuell))
			{	
				aktuellePos=findeZahl(liste,funktion,aktuellePos,aktuell);
				continue;
			}
			if(aktuell=='(')
			{
				liste.add(Klammern.OFF);
				aktuellePos++;
				
			}
			if(aktuell==')')
			{
				liste.add(Klammern.SCHLIES);
				aktuellePos++;
			}
			else
			{	// Das aktuelle Token wird mit jedem in Maps verglichen
				for(Map<String,? extends Token> map : maps)
				{
					int tokenLaenge;
					tokenLaenge=erkenneToken(map,aktuellePos,funktion,liste);
					if (tokenLaenge!=0)
					{
						aktuellePos=aktuellePos+tokenLaenge;
					}
					
				}
			}
			
		}
		return liste;
	}
	
	public int findeZahl(List<Token> liste ,String funktion,int aktuellePos,char aktuell)
	{
		
		Double ergebnis;
		boolean punkt=false;
		int nachfolger=aktuellePos+1;
		while(nachfolger<funktion.length())
		{
			char nachfolgeChar=funktion.charAt(nachfolger);
			if (Character.isDigit(nachfolgeChar))
			{
				nachfolger++;
			}
			else if(!punkt && nachfolgeChar== '.')
			{
				punkt=true;
				nachfolger++;
			}
			else
			{
				break;
			}
		}
			
			try
			{
				ergebnis=Double.valueOf(funktion.substring(aktuellePos, nachfolger));
				liste.add(new ZahlToken(ergebnis));
			}
			catch (NumberFormatException e)
			{
				System.out.println(e.toString());
				System.out.println("Es ist eine Parse Exeption aufgetreten");
			}
			
		
		return nachfolger;
	}
	
	
	public int erkenneToken(Map<String, ? extends Token> m,int aktuellePos, String funktion, List<Token> liste)
	{
		int tokenLaenge=0;
		
		for(String s: m.keySet())
		{
			if (funktion.startsWith(s, aktuellePos))
			{
				liste.add(m.get(s));
				tokenLaenge=+s.length();
				return tokenLaenge;
			}
		}
		
		
		return 0;
	}
	
	// TODO implizite Multiplikation zB. 2x+4
	public List<Token> ersetzeX(int xwert, List<Token> liste)
	{
		List<Token> ergebnis= new ArrayList<Token>(liste.size());
		for(int i=0;i<liste.size();i++)
		{
			if(liste.get(i) instanceof XToken)
			{
				ergebnis.add(i, new ZahlToken(xwert));
				
			}
			else
			{
				ergebnis.add(i, liste.get(i));
			}
		}
		return ergebnis;
		
	}
	
	public List<Token> infixNachUpn(List<Token> liste)
	{
		List <Token> upnListe= new ArrayList<Token>();
		
		Stack operanten = new Stack(liste.size());
		ArrayBlockingQueue<Token> ausgabe = new ArrayBlockingQueue<Token>(liste.size());
		
		int pos=0;
		
		while(pos<liste.size())
		{
			Token taktuell=liste.get(pos);
			if(taktuell instanceof ZahlToken || taktuell instanceof XToken)
			{
				ausgabe.add(taktuell);
				pos++;
				continue;
			}
			if(taktuell.equals(Klammern.OFF))
			{
				operanten.push(taktuell);
				pos++;
				continue;
				
			}
			if(taktuell instanceof Operation)
			{
				Operation optaktuell=(Operation)taktuell;
				
				Operation oben=null;
				
				if (operanten.readStack() instanceof Operation)
				{
				oben = (Operation) operanten.readStack();
				}
				
				while(!operanten.empty() && operanten.readStack() instanceof Operation &&
						(optaktuell.getAsso()==1 && optaktuell.getPrioritaet()<=oben.getPrioritaet() || 
						optaktuell.getPrioritaet()<oben.getPrioritaet()))
				{
					ausgabe.add((Token)operanten.pop());
				}
				operanten.push(taktuell);
				pos++;
				continue;
			}
			if(taktuell.equals(Klammern.SCHLIES))
			{
				while(!operanten.readStack().equals(Klammern.OFF))
				{
					ausgabe.add((Token) operanten.pop());
				}
				operanten.pop();
				pos++;
				continue;
				
			}
			
		}
		
		while(!operanten.empty())
		{
			ausgabe.add((Token) operanten.pop());
		}
		
		for(Token t: ausgabe)
		{
			upnListe.add(t);
		}
		
		return upnListe;
		
	}
	
	public double upnNachDouble(List<Token> upnListe)
	{
		Stack s = new Stack(upnListe.size());
		
		for(Token t: upnListe)
		{
			if(t instanceof ZahlToken)
			{
				s.push(t);
			}
			
			if(t instanceof Operation)
			{
				ZahlToken rechts = (ZahlToken)s.pop();
				ZahlToken links = (ZahlToken)s.pop();
				
				Operation o= (Operation)t;
				double berechnung= o.berechne(links.getZahl(),rechts.getZahl());
				s.push(new ZahlToken(berechnung));
			}
		}
		return ((ZahlToken)s.pop()).getZahl();
	}

	

	
}
