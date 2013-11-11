package model;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class FktBerechnungen {
	private Map<String,Operation> rechenoperationen;
	private Map<String, ZahlToken> konstanten;
	private List<Map<String, ? extends Token>> maps;
	private String funktion;
	private List<Token> tokenliste;
	private List<Token> upnListe;
	
	// Prüfung auf Art der Zeichen
	
	// TODO falls Zeit: try catch zur Ablaufsteuerung entfernen, Verrechnungen entfernen/ Vereinfachen?
	// Prüfung auf Zeichenreihenfolge (doppelte Rechenzeichen oder == )
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
	public void tokenize()
	{	
		// Maps die alle Operationen,Konstanten enthalten
		 rechenoperationen = new HashMap<String, Operation>();
	     konstanten = new HashMap<String, ZahlToken>();
	     maps = new ArrayList<Map<String, ? extends Token>>();
	     
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
		tokenliste=liste;
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
	public List<Token> ersetzeX(double xwert, List<Token> xListe)
	{
		List<Token> ergebnis= new ArrayList<Token>(xListe.size());
		for(int i=0;i<xListe.size();i++)
		{
			if(xListe.get(i) instanceof XToken)
			{
				ergebnis.add(i, new ZahlToken(xwert));
				
			}
			else
			{
				ergebnis.add(i, xListe.get(i));
			}
		}
		return ergebnis;
		
	}
	
	public void infixNachUpn()
	{
		 upnListe= new ArrayList<Token>();
		
		Stack operanten = new Stack(tokenliste.size());
		ArrayBlockingQueue<Token> ausgabe = new ArrayBlockingQueue<Token>(tokenliste.size());
		
		int pos=0;
		
		while(pos<tokenliste.size())
		{
			Token taktuell=tokenliste.get(pos);
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
		
		
		
	}
	
	public double upnNachDouble(List<Token> doublelist)
	{
		Stack s = new Stack(doublelist.size());
		
		for(Token t: doublelist)
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

	
	public double[][] berechneFunktion(String funktione, double xmin, double xmax)
	{
		funktion=funktione;
		tokenize();
		infixNachUpn();
		
		int stuetzpunkte = 0;
		double differenz =(xmax-xmin);
		stuetzpunkte = (int)(differenz/0.1);
		
		double[][] ergebnis= new double[stuetzpunkte][2];
		
		for(int i=0;i<stuetzpunkte;i++)
		{
			ergebnis[i][0]=xmin+(i*0.1);
			List<Token> x= ersetzeX((xmin+i*0.1),upnListe);
			ergebnis[i][1]=upnNachDouble(x);
		}
		
		return ergebnis;
		
	}
	
}
