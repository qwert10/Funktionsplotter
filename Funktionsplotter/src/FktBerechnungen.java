
public class FktBerechnungen {
	
	// Prüfung auf Art der Zeichen
	// Prüfung auf Zeichenreihenfolge (doppelte ^^ oder ++ -- ** // ^+ +/ +- )
	public boolean doppelteZeichen(String funktion)
	{
		if (funktion.length()==0 || funktion.length()==1)
			return true;
		
		for(int i=0;i<=funktion.length();i++)
		{
			char test;
			char next;
			try
			{
			 test =funktion.charAt(i);
			 next =funktion.charAt(i+1);
			}
			
			catch(StringIndexOutOfBoundsException e)
			{
				return true;
			}
			
			if (test=='+' || test=='-' || test=='*' || test=='/')
			{
				if (next=='+' || next=='-' || next=='*' || next=='/')
				{
					return false;
				}
				
			}
			
			
		}
		
		return true;
	}
	public void fktteilen(String funktion)
	{	
		
	}
	
	public void reihenfolge(String funktion)
	{
		
	}
	
	public boolean klammernPruefen(String s)
	{
		ParenMatcher pm = new ParenMatcher();
		pm.setInput(s);
		return(pm.parenMatch());
	}
	
}
