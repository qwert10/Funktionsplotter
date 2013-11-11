package model;

public class Stack {
	
	private Object[] Stapel;
	
	public Stack(int size)
	{
		Stapel= new Object[size];
		
	}
	
	public boolean empty() 
	{
		for (int i=0;i<=Stapel.length-1;i++)
		{
			if (Stapel[i]!=null)
				return false;
		}
		return true;
		
	}
	
	public void push(Object x){
		
		for (int k=0;k<=Stapel.length;k++)
		{
			if (Stapel[k]==null)
			{	Stapel[k]=x;
				break;}
		}
		
	}
	
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