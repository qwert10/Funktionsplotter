package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import View.GUI;
import model.FktBerechnungen;

/**
 * Controller für den Funktionsplotter. Verarbeitet Eingaben der Buttons und leitet die entsprechenden Reaktionen ein.
 * Anschließend werden die Ergebnisse in der GUI angezeigt.
 * @author smodlich
 * 
 */
public class FktController {
	/**
	 * Funktionsberechnungklasse
	 */
	private FktBerechnungen berechnung;
	/**
	 * GUI Klasse
	 */
	private GUI gui;
	
	/**
	 * Konstruktor der die Berechnungsvariable und die GUI setzt
	 * @param f Funktionsberechnungsklasse
	 * @param g GUI Objekt
	 */
	public FktController(FktBerechnungen f, GUI g)
	{
		berechnung=f;
		gui=g;
		this.gui.addZureckSetzenListener(new ZuSetzListener());
		this.gui.addLoeschenListener(new LoeschenListener());
		this.gui.addZeichenListener(new ZeichenListener());
		this.gui.addZoomInListener(new ZoomInListener());
		this.gui.addZoomOutListener(new ZoomOutListener());
		
	}

	/**
	 * Listener für den Zeichnen Button. Reagiert wenn der Zeichnen Button geklickt wird.
	 * Liest ymax, ymin, xmax, xmin aus und setzt sie in der FktFlaeche Klasse. Nimmt Ergebnis der Berechnungsklasse an und
	 * setzt es in der FktFlache Klasse. Löst Repaint aus.
	 * 
	 * @author smodlich
	 *
	 */

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
			gui.displayErrorMessage("Ganzzahl eingeben für XMin, Xmax, Ymin, Ymax und Stützpunkte!");
			return;
		}

		double[][] pkt =null;
		try {
			pkt = berechnung.berechneFunktion(funktion, xmin, xmax);
		} catch (Exception e) {
			
			gui.displayErrorMessage("Parse Fehler" + e.getMessage());
		}
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		gui.getZeichenflaeche().repaint();
		
	}

}
/**
 * Listener des Löschen Buttons. Setzt Zeichenfläche der GUI auf null.
 * @author smodlich
 *
 */
class LoeschenListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.getZeichenflaeche().setPunkte(null);
		gui.getZeichenflaeche().repaint();

	}
}

/**
 * Listener für Zoom In Button. Liest min und max Werte aus. Multipliziert sie anschließend mit 1,2 und rundet.
 * @author smodlich
 *
 */
class ZoomInListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		double ymax,ymin,xmin,xmax;
		int stuetzpunktzahl=0;
		xmax=0;xmin=0;ymax=0;ymin=0;
		try{
			ymax=gui.getYmax();
			ymin=gui.getYmin();
			xmax=gui.getXmax();
			xmin=gui.getXmin();
		}
		catch(NumberFormatException ex){
			gui.displayErrorMessage("Zahl eingeben für XMin, Xmax, Ymin, Ymax!");
			
		}
		ymax=(Math.round((ymax*0.5)*100))/100.0;
		ymin=(Math.round((ymin*0.5)*100))/100.0;
		xmin=(Math.round((xmin*0.5)*100))/100.0;
		xmax=(Math.round((xmax*0.5)*100))/100.0;
		
		
		gui.getXmaxButton().setText(""+xmax);
		gui.getXminButton().setText(""+xmin);
		gui.getYmaxButton().setText(""+ymax);
		gui.getYminButton().setText(""+ymin);
		
		gui.getZeichenflaeche().setMinMax(ymax, ymin, xmax, xmin);
		double[][] pkt =null;
		try {
			pkt = berechnung.berechneFunktion(gui.getFunction(), xmin, xmax);
		} catch (Exception ex) {
			
			gui.displayErrorMessage("Parse Fehler: "+ex.getMessage());
		}
		gui.getZeichenflaeche().setPunkte(pkt);
		gui.getZeichenflaeche().repaint();
	}
	
}
/**
 * Listener für Zoom Out Button. Liest min und max Werte aus. Multipliziert sie anschließend mit 0,8 und rundet.
 * @author smodlich
 *
 */
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
			gui.displayErrorMessage("Zahl eingeben für XMin, Xmax, Ymin, Ymax!");
			return;
		}
		ymax=(Math.round((ymax*2)*100))/100.0;
		ymin=(Math.round((ymin*2)*100))/100.0;
		xmin=(Math.round((xmin*2)*100))/100.0;
		xmax=(Math.round((xmax*2)*100))/100.0;
		
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
/**
 * Listener für Zurücksetzen Button. Setzt auf xmin: -5 xmax: 5 ymin: -6 und ymax: 5 zurück.
 *
 */
class ZuSetzListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		double xmax=5;
		double xmin=-5;
		double ymax= 5;
		double ymin= -5;
		
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

