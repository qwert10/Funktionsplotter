
public class FktBerechnungen {

	public void fktteilen(String funktion)
	{	//Eingabepr�fung
		
		//Klammern pr�fen
		ParenMatcher pm = new ParenMatcher();
		pm.setInput(funktion);
		pm.parenMatch();
		// Funktion mathematisch vereinfachen
		
	}
}
