package model;

/**
 * Klasse die für die Klammern nötig ist implementiert das Token Interface.
 * @author smodlich
 *
 */
public enum Klammern implements Token {
	/**
	 * öffnende Klammer
	 */
	OFF("("),
	/**
	 * schließende Klammer
	 */
	SCHLIES(")");
	
	public final String zeichen;
	
	/**
	 * interner Konstruktor für Klammern enum Objekt
	 * @param z Klammer
	 */
	Klammern(String z)
	{
		zeichen=z;
	}
	/**
	 * Gibt Klammerwert zurück
	 * @return Klammer
	 */
	public String getZeichen()
	{
		return zeichen;
	}
}
