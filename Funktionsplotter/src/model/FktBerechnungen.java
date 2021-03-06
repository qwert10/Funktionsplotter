package model;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Zentrale Berechnungsklasse. Der Zugang von außen ist durch die Funktion berechnetFunktion gekapselt.
 * Hält Funktionen zum zersetzen (tokenizen) des Eingabestrings bereit und zur Umwandlung von Infix in umgekehrte polnische Notation
 * und zur Berrechnung von Funktionen bereit.
 * @author smodlich
 *
 */
public class FktBerechnungen {
	/**
	 * Rechenoperation Map enthält alle Rechenoperationen
	 *
	 */
	private Map<String,Operation> rechenoperationen;
	/**
	 * Konstanten Map enthält die Konstanten e und pi.
	 */
	private Map<String, ZahlToken> konstanten;
	/**
	 * Map über alle Maps enthält Operatoren und Konstanten und Klammern
	 */
	private List<Map<String, ? extends Token>> maps;
	/**
	 * Liste der erkannten Token in Infix Notation
	 */
	private List<Token> tokenliste;
	/**
	 * Liste der erkannten Token nach Umwandlung in UPN Notation.
	 */
	private List<Token> upnListe;	
	/**
	 * Funktionsstring
	 */
	private String funktion;
	
	/**
	 * Zersetzt den Eingabestring, Varible funktion dieser Klasse, zu einer Liste von Tokens
	 * @throws TokenizeException
	 */
	public void tokenize() throws TokenizeException
	{	
		// Maps die alle Operationen,Konstanten, Funktionen enthalten
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
	     konstanten.put("pi", new ZahlToken(Math.PI));
	     konstanten.put("e", new ZahlToken(Math.E));
	     
	     // Liste der Maps fuellen
	     maps.add(rechenoperationen);
	     maps.add(konstanten);
		
		//Ergebnisliste
		List<Token> liste= new ArrayList<Token>();
		
		// aktuelle Position
		int aktuellePos=0;
		
		
		//Schleife �ber alle Zeichen des Eingabestrings
		aussen:
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
				continue;
				
			}
			if(aktuell==')')
			{
				liste.add(Klammern.SCHLIES);
				aktuellePos++;
				continue;
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
						continue aussen;
					}
					
					
				}	
					throw new TokenizeException("Zeichen an Stelle: " + aktuellePos +" konnte nicht erkannt werden!");
			}
			
		}
		tokenliste=liste;
	}
	/**
	 * Funktion, die Zahlen im Eingabestring findet insbesondere Zahlen mit enthaltenem Komma
	 * @param liste Die Eingabeliste
	 * @param funktion der Funktionsstring
	 * @param aktuellePos aktuelle Position in der eine Zahl gefunden werden soll
	 * @param aktuell char auf der Position aktuell
	 * @return gibt die Endposition einer Zahl wieder
	 * @throws NumberFormatException
	 */
	
	public int findeZahl(List<Token> liste ,String funktion,int aktuellePos,char aktuell) throws NumberFormatException
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
				System.out.println("Es ist eine Zahlen Parse Exeption aufgetreten Position: " + aktuellePos);
			}
			
		
		return nachfolger;
	}
	
	/**
	 * Erkennt ein Token (Funktion oder Operation) indem es durch die gesamt Tokenliste iteriert und prüft ob das Token eines
	 * der Tokens in der Liste ist. Wenn dies der Fall ist, das gefundene Token der Liste hinzugefügt.
	 * @param m Map, in der alle Tokens enthalten sind
	 * @param aktuellePos die aktuelle Position in der ein Token gefunden werden soll
	 * @param funktion der String der verarbeitet wird
	 * @param liste die Tokenliste zu der das gefundene Token hinzugefügt wird
	 * @return gibt Index des Endes des gewählen Tokens zurück
	 */
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
	
	/**
	 * Ersetzt in der Liste xListe alle XToken durch das ZahlToken mit dem Wert xwert
	 * @param xwert der Wert der für X eingesetzt werden soll
	 * @param xListe Liste in der X ersetzt werden soll
	 * @return die Liste mit den Ersetzungen
	 */
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
	
	/**
	 * Ersetzt in der Liste tokenlist alle Kombinationen (von links): ZahlToken XToken durch ZahlToken MAL XToken
	 */
	public void implizitMulti()
	{	
		
		for (int i = 0; i < tokenliste.size()-1; i++) {
			
			if(tokenliste.get(i) instanceof ZahlToken)
			{
				if(tokenliste.get(i+1) instanceof XToken)
				{
					tokenliste.add(i+1, Operation.MAL);
				}
				
			}
			
		}
		
	}
	/**
	 * Wandelt die tokenliste von der Infix Notation in die umgekehrte polnische Notation um. Dazu wird der Shunting-Yard Algorithmus
	 * verwendet. Das Ergebnis wird in der upnListe gespeichert.
	 */
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
	/**
	 * Benötigt eine UPN-Liste als Eingabeliste. Wertet die UPN Liste aus, d.h. errechnet ein Ergebnis des Ausdruckes. 
	 * @param doublelist die UPN Liste, deren Wert errechnet werden soll.
	 * @return Ergebnis der Auswertung als Double Zahl
	 */
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

	/**
	 * Berrechnet zwischen xmin und xmax mit 1000 Stützpunkten die Funktionswerte.
	 * Funktion die die Funktionen dieser Klasse in der richtigen Reihenfolge zusammenfasst, so dass kein Vorwissen nötig ist um die
	 * Funktionen für einen Funktionsplotter zu benutzen.
	 * @param Funktion der Funktionsstring
	 * @param xmin das Miniumum der xWerte
	 * @param xmax das Maximum der XWerte
	 * @return ein Array, dass die XWerte und die zugehörigen YWerte enthält 
	 * @throws Exception
	 */
	public double[][] berechneFunktion(String Funktion, double xmin, double xmax) throws Exception
	{
		try
		{
		funktion=Funktion;
		tokenize();
		}
		catch(TokenizeException t)
		{
			throw t;
		}
		
		ParenMatcher pm = new ParenMatcher();
		pm.setInput(funktion);
		if(!pm.parenMatch())
		{
			throw new Exception("Falsche Klammern!");
		}
		implizitMulti();
		try
		{
			infixNachUpn();
			int stuetzpunkte = 0;
			double differenz =(xmax-xmin);
			double abstand=0.001;
			stuetzpunkte = (int)(differenz/abstand);
			double[][] ergebnis= new double[stuetzpunkte][2];
			
			for(int i=0;i<stuetzpunkte;i++)
			{
				ergebnis[i][0]=xmin+(i*abstand);
				List<Token> x= ersetzeX((xmin+i*abstand),upnListe);
				ergebnis[i][1]=upnNachDouble(x);
			}
			
			return ergebnis;
		}
		catch (Exception e)
		{
			throw new Exception(" Fehler beim Parsen. Falsche Zeichenreihenfolge? ");
		}
		
		
			
			
		
		
		
		
		
		
	}
	
}
