import java.util.ArrayList;
import java.util.List;


public class FktBerechnungMainTest {

	public static void main(String[] args)
	{
		FktBerechnungen f =new FktBerechnungen();
		List<Token> liste = f.tokenize("");
		List<Token> zwei = f.infixNachUpn(liste);
		System.out.println(f.upnNachDouble(zwei));
	
		

	}	
}
