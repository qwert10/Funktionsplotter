import java.util.List;


public class FktBerechnungMainTest {

	public static void main(String[] args)
	{
		FktBerechnungen f =new FktBerechnungen();
		List<Token> liste = f.tokenize("4+8");
		System.out.println(liste.toString());
	}	
}
