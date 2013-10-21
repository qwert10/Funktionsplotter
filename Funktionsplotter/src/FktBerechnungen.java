import java.util.*;


public class FktBerechnungen {
	
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
	public List<Token> tokenize(String funktion)
	{	
		// Maps die alle Operationen,Konstanten enthalten
		 Map<String,Operation> rechenoperationen = new HashMap<String, Operation>();
	     Map<String, ZahlToken> konstanten = new HashMap<String, ZahlToken>();
	     List<Map<String, ? extends Token>> maps = new ArrayList<Map<String, ? extends Token>>();
	     
	     // Operationsliste füllen
	     for(Operation op:Operation.values())
	     {
	    	 rechenoperationen.put(op.getName(), op);
	     }
	     
	     // Einfügen von Konstanten
	     konstanten.put("PI", new ZahlToken(Math.PI));
	     konstanten.put("E", new ZahlToken(Math.E));
	     
	     // Liste der Maps füllen
	     maps.add(rechenoperationen);
	     maps.add(konstanten);
		
		//Ergebnisliste
		List<Token> liste= new ArrayList<Token>();
		
		// aktuelle Position
		int aktuellePos=0;
		
		
		//Schleife über alle Zeichen des Eingabestrings
		while(aktuellePos<funktion.length())
		{
			char aktuell= funktion.charAt(aktuellePos);
			
			if (Character.isDigit(aktuell))
			{	
				aktuellePos=findeZahl(liste,funktion,aktuellePos,aktuell);
			}
			if(aktuell=='(')
			{
				liste.add(Klammern.OFF);
				
			}
			if(aktuell==')')
			{
				liste.add(Klammern.SCHLIES);
			}
			else
			{
				for(Map<String,? extends Token> map : maps)
				{
					int tokenLaenge;
					tokenLaenge=erkenneToken(map,aktuellePos,funktion,liste);
					
				}
			}
			
		}
		return liste;
	}
	
	// TODO Prüfung der Funktion! 
	public int findeZahl(List<Token> liste ,String funktion,int aktuellePos,char aktuell)
	{
		
		Double ergebnis;
		boolean punkt=false;
		int nachfolger=aktuellePos+1;
		while(nachfolger<funktion.length())
		{
			char nachfolgeChar=funktion.charAt(nachfolger);
			if (Character.isDigit(nachfolger))
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
	
	public void infixNachUpn()
	{
		
	}
	
	public void upnNachDouble()
	{
		
	}

	

	
}
