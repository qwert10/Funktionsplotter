package model;


public class FktBerechnungMainTest {

	public static void main(String[] args)
	{
		
			FktBerechnungen f =new FktBerechnungen();
			double[][] d= f.berechneFunktion("x*x", 2, 4);
		
			for (double[] test:d )
			{
				System.out.println(test[0]+"  "+test[1]);
			}

	}	
}
