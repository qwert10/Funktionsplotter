package model;

/**
 * abstrakte Hülle für Zahlen
 * @author smodlich
 * final double zahl: Wert dieses Zahltokens (nicht veränderlich) 
 */
public class ZahlToken implements Token {

	private final double zahl;

	public ZahlToken(double nummer)
	{
		zahl=nummer;
	}
	
	public double getZahl()
	{
		return zahl;
	}
}
