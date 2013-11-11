package model;

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
