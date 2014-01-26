package model;


public class FktBerechnungMainTest {

	public static void main(String[] args) throws Exception
	{
		
			FktBerechnungen f =new FktBerechnungen();
		/*	double[][] d= f.berechneFunktion("x*x", 2, 4);

			int i=0;
			
			double[] xPoints= new double[d.length];
			double[] yPoints= new double[d.length];
			
			for (double[] c:d)
			{
				
				xPoints[i]=c[0];
				yPoints[i]=c[1];
				i++;
			}
			for (double x:xPoints)
			{System.out.println(x);}
			for (double y:yPoints)
			System.out.println(y);
			
			*/
			f.berechneFunktion("x+2", 2, 6);
			

	}	
	
}
