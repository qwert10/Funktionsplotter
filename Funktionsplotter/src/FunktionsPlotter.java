import controller.FktController;
import View.GUI;
import model.FktBerechnungen;


public class FunktionsPlotter {
	
	FktBerechnungen f = new FktBerechnungen();
	GUI g = new GUI();
	FktController c = new FktController(f,g);
	
	

	public static void main(String[] args)
	{
		FunktionsPlotter f = new FunktionsPlotter();
	}
}

