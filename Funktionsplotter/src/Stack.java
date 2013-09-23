
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
	/*
	public static void main (String args[])
	{
		
		Stack s1= new Stack(10);
		int eins =1;
		int zwei =2;
		double drei =3;
		double vier=4;
		
		s1.push(eins);
		s1.push(zwei);
		s1.push(drei);
		s1.push(vier);
		System.out.println(s1.empty());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.empty());
		
	}
	*/
}