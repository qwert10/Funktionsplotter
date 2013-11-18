package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.GUI;
import model.FktBerechnungen;

public class FktController {
	private FktBerechnungen berechnung;
	private GUI gui;
	
	public FktController(FktBerechnungen f, GUI g)
	{
		berechnung=f;
		gui=g;
		

		this.gui.addLoeschenListener(new LoeschenListener());
		this.gui.addZeichenListener(new ZeichenListener());
	}


class ZeichenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		double ymax;
		double ymin;
		double xmax;
		double xmin;
		String funktion;
		
		funktion=gui.getFunction();
		try{
			ymax=gui.getYmax();
			ymin=gui.getYmin();
			xmax=gui.getXmax();
			xmin=gui.getXmin();
		}
		catch(NumberFormatException e){
			gui.displayErrorMessage(e.toString());
			return;
		}

		double[][] pkt = berechnung.berechneFunktion(funktion, xmin, xmax);
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		gui.getZeichenflaeche().repaint();
		
	}

}
class LoeschenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
}