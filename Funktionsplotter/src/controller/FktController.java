package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		this.gui.addZoomInListener(new ZoomInListener());
		this.gui.addZoomOutListener(new ZoomOutListener());
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

		double[][] pkt =null;
		try {
			pkt = berechnung.berechneFunktion(funktion, xmin, xmax);
		} catch (Exception e) {
			
			gui.displayErrorMessage(e.getMessage());
		}
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		gui.getZeichenflaeche().repaint();
		
	}

}
class LoeschenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getZeichenflaeche().setPunkte(null);
		gui.getZeichenflaeche().repaint();

	}
}

class ZoomInListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		double ymax,ymin,xmin,xmax;
		ymax=0;
		xmax=0;
		ymin=0;
		xmin=0;
		
		try{
			ymax=gui.getYmax();
			ymin=gui.getYmin();
			xmax=gui.getXmax();
			xmin=gui.getXmin();
		}
		catch(NumberFormatException ex){
			gui.displayErrorMessage(ex.toString());
			
		}
		ymax=(Math.round((ymax*1.6)*100))/100.0;
		ymin=(Math.round((ymin*1.6)*100))/100.0;
		xmin=(Math.round((xmin*1.6)*100))/100.0;
		xmax=(Math.round((xmax*1.6)*100))/100.0;
		
		
		gui.getXmaxButton().setText(""+xmax);
		gui.getXminButton().setText(""+xmin);
		gui.getYmaxButton().setText(""+ymax);
		gui.getYminButton().setText(""+ymin);
		
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		double[][] pkt =null;
		try {
			pkt = berechnung.berechneFunktion(gui.getFunction(), xmin, xmax);
		} catch (Exception ex) {
			
			gui.displayErrorMessage(ex.getMessage());
		}
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().repaint();
	}
	
}

class ZoomOutListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		double ymax;
		double ymin;
		double xmax;
		double xmin;
		try{
			ymax=gui.getYmax();
			ymin=gui.getYmin();
			xmax=gui.getXmax();
			xmin=gui.getXmin();
		}
		catch(NumberFormatException ex){
			gui.displayErrorMessage(e.toString());
			return;
		}
		ymax=(Math.round((ymax*0.625)*100))/100.0;
		ymin=(Math.round((ymin*0.625)*100))/100.0;
		xmin=(Math.round((xmin*0.625)*100))/100.0;
		xmax=(Math.round((xmax*0.625)*100))/100.0;
		
		gui.getXmaxButton().setText(""+xmax);
		gui.getXminButton().setText(""+xmin);
		gui.getYmaxButton().setText(""+ymax);
		gui.getYminButton().setText(""+ymin);
		
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		double[][] pkt =null;
		try {
			pkt = berechnung.berechneFunktion(gui.getFunction(), xmin, xmax);
		} catch (Exception ex) {
			
			gui.displayErrorMessage(ex.getMessage());
		}
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().repaint();
		
	}
	
		
	}
	
}

