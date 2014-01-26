package model;

public enum Klammern implements Token {
	OFF("("),SCHLIES(")");
	
	public final String zeichen;
	
	private Klammern(String z)
	{
		zeichen=z;
	}
	public String getZeichen()
	{
		return zeichen;
	}
}
