import java.util.ArrayList;
import java.util.List;


public class FktBerechnungMainTest {

	public static void main(String[] args)
	{
		FktBerechnungen f =new FktBerechnungen();
			List<Token> liste = f.tokenize("x*x*x*x-4+1");
			List<Token> zwei = f.infixNachUpn(liste);
			for(int i=0; i<50;i++)
			{
				List<Token> drei= f.ersetzeX(i, zwei);
				System.out.println("X Wert: "+i+" Y-Wert:" +f.upnNachDouble(drei));
			}

	
		

	}	
}
