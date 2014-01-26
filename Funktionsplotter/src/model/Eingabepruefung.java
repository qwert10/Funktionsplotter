package model;

public class Eingabepruefung {
	
	// Prüfung auf doppelte Rechenzeichen
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
	
	

	
	// entfernt alle Sonderzeichen und Leerzeichen (zB. Backslash etc.)
	public void entferneSonstigeZeichen(String funktion)
	{
		char test;
		for(int i=0;i<=funktion.length()-1;i++)
		{
			test=funktion.charAt(i);
	    
			if(!(Character.isLetterOrDigit(test) || test=='+' || test=='-' || test=='*' || test=='/'|| test=='(' || test==')' || test=='.'))
			{
	    	funktion.replace(test, ' ');
			}
	    
		}
		
		funktion.replaceAll("\\s", "");
		funktion.replaceAll("\\t", "");
	}
	
}
