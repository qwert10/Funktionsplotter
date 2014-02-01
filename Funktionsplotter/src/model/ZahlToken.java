package model;

/**
 * abstrakte Hülle für Zahlen
 * @author smodlich
 * final double zahl: Wert dieses Zahltokens (nicht veränderlich) 
 */
public class ZahlToken implements Token {

	/**
	 * Der Wert dieses Zahltokens.
	 */
	private final double zahl;

	/**
	 * Erstellt ein neues Zahltoken mit dem Wert nummer
	 * @param nummer Wert des Zahltokens
	 */
	public ZahlToken(double nummer)
	{
		zahl=nummer;
	}
	/**
	 * Gibt den Wert des Tokens zurück
	 * @return Wert des Tokens
	 */
	public double getZahl()
	{
		return zahl;
	}
}
