package model;
/**
 * Stellt einen Stapel dar. Es gilt das FIFO Pinzip.
 * @author smodlich
 *
 */
public class Stack {
	
	private Object[] Stapel;
	
	/**
	 * legt einen Stapel mit fester Größe an
	 * @param size
	 */
	public Stack(int size)
	{
		Stapel= new Object[size];
		
	}
	
	/**
	 * prüft ob der Stapel leer ist
	 * @return true wenn Stapel leer ist.
	 */
	public boolean empty() 
	{
		for (int i=0;i<=Stapel.length-1;i++)
		{
			if (Stapel[i]!=null)
				return false;
		}
		return true;
		
	}
	/**
	 * Legt das Object x oben auf den Stapel
	 * @param x das Object das oben auf den Stapel gelegt wird.
	 */
	public void push(Object x){
		
		for (int k=0;k<=Stapel.length;k++)
		{
			if (Stapel[k]==null)
			{	Stapel[k]=x;
				break;}
		}
		
	}
	
	/**
	 * gibt das oberste Object des Stapels zurück und entfernt es aus dem Stapel.
	 * @return
	 */
	public Object pop(){
		if (Stapel.length==1)
			return(Stapel[0]);
		
		Object puffer;
		
		int j;
		
		for (j=0;j<=Stapel.length;j++)
		{
		 if (Stapel[j]==null)
			 break;
			
		}
		j--;
		
		puffer=Stapel[j];
		Stapel[j]=null;
		return puffer;	
	}
	/**
	 * Lesender Zugriff auf den Stapel (wird von Shunting Yard Alg. benötigt). 
	 * @return das oberste Obekt des Stapels ohne es zu entfernen. Oder null wenn der Stapel leer ist.
	 */
	public Object readStack()
	{
		if (empty())
		return null;
		
		int j=0;
		for (j=0;j<=Stapel.length;j++)
		{
		 if (Stapel[j]==null)
			 break;
			
		}
		j--;
		
		return Stapel[j];
		
	}
}