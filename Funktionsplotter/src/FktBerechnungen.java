
public class FktBerechnungen {

	public void fktteilen(String funktion)
	{	//Eingabeprüfung
		
		//Klammern prüfen
		ParenMatcher pm = new ParenMatcher();
		pm.setInput(funktion);
		pm.parenMatch();
		// Funktion mathematisch vereinfachen
		
	}
}
