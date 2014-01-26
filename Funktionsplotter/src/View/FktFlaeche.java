package View;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.text.DecimalFormat;

/**
 * Zeichenfläche für die Funktion.
 * @author smodlich
 * double[][] punkte: Array mit den X und Y Werten
 * double ymax Maximum der Y Werte
 * double ymin Minimum der Y Werte
 * double xmax Maximum der X Werte
 * double xmin Minimum der X Werte
 */

public class FktFlaeche extends JPanel {
	private double ymax;
	private double ymin;
	private double xmax;
	private double xmin;
	private double[][] punkte;

	/**
	 * Paint Component Methode der Oberklasse wird überschrieben um Funktion zu Zeichnen ruft vorbereitungs Methode auf.s
	 */
	public void paintComponent(Graphics g)
	{ 
		vorbereitung(g);
		if(punkte!=null)
		{
		
		g.translate(-10,-10);
		g.setColor(Color.RED);
		
		double[] xPoints= new double[punkte.length];
		double[] yPoints= new double[punkte.length];
		
		int i=0;
		
		for (double[] d:punkte)
		{
			
			xPoints[i]=d[0];
			yPoints[i]=d[1];
			i++;
		}
		
		double xdifferenz=Math.abs(xmax)+Math.abs(xmin);
		double ydifferenz=Math.abs(ymax)+Math.abs(ymin);
		
		int[] xPixel=new int[xPoints.length];
		int[] yPixel=new int[yPoints.length];
		
		for(int j=0; j<xPixel.length;j++)
		{
			
			xPixel[j]= (int) (((super.getWidth()-20)*xPoints[j])/xdifferenz);
		}
		
		for(int j=0; j<yPixel.length;j++)
		{
			yPixel[j]=(int) (((super.getHeight()-20)*yPoints[j])/ydifferenz);
		}
		for (int j=0;j<yPixel.length; j++)
		{
			yPixel[j]=-yPixel[j];
		}
		
		g.drawPolyline(xPixel, yPixel, xPixel.length);
		}
		
	}
	/**
	 * setzt die entsprechenden Minima und Maxima für die Zeichnung
	 * @param yMax
	 * @param yMin
	 * @param xMax
	 * @param xMin
	 */
	public void setMinMax(double yMax,double yMin, double xMax, double xMin)
	{
		ymax=yMax;
		ymin=yMin;
		xmin=xMin;
		xmax=xMax;
	}

	/**
	 * setzt das Array der XWerte und der Funktionswerte
	 * @param pkt
	 */
	public void setPunkte(double[][] pkt)
	{
		punkte=pkt;
	}
	
	/**
	 * Ruft Beschriftungsfunktion auf und zeichnet Achsen mit Zehnereinteilung.
	 * @param g das Graphics Objekt auf dem gezeichnet wird.
	 */
	public void vorbereitung(Graphics g)
	{
		int width=super.getWidth()-20;
		int height=super.getHeight()-20;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 520, 520);
		g.setColor(Color.GRAY);
		g.translate(10, 10);
		g.drawLine(0,height/2, width, height/2); // X-Achse
		g.drawLine(width/2,0, width/2, height); // Y-Achse
		
		for(int i=0;i<11;i++)
		{
			g.drawLine(i*(width/10), (height/2)-5,i*(width/10),(height/2)+5);
			g.drawLine((width/2)-5,i*height/10,(width/2)+5,i*height/10);
		}
		
		beschriftung(g);
		
		g.translate(super.getSize().width / 2, super.getSize().height / 2);
	}
	/**
	 * Zeichnet die Beschriftung der Achsen mit xmin, xmax, ymin, ymax
	 * @param g, dass Graphics Objekt auf dem gezeichnet wird.
	 */
	public void beschriftung(Graphics g)
	{
		int width= super.getWidth()-20;
		int height= super.getHeight()-20;
		
		if (xmin!=0 && xmax!=0 && ymin!=0 && ymax !=0)
		{
			double xdifferenz= xmax-xmin;
			double xschritt = xdifferenz/10;
			double[] xbeschriftung=new double[11];
			
			for (int i=0; i<xbeschriftung.length;i++)
			{
				xbeschriftung[i]=xmin+i*xschritt;	
			}
			
			DecimalFormat x =new DecimalFormat();
			
			for (int i=0;i<11;i++)
			{
				if(xbeschriftung[i]!=0)
				{
				g.drawString(""+x.format(xbeschriftung[i]), i*width/10, height/2+20);
				}
			}
			
			double ydifferenz=ymax-ymin;
			double yschritt = ydifferenz/10;
			double[]  ybeschriftung =new double[11];
			double[] yinvertiert= new double[11];
			
			for (int i=0;i<ybeschriftung.length;i++)
			{
				ybeschriftung[i]=ymin+i*yschritt;
			}
			for (int i=0;i<ybeschriftung.length;i++)
			{
				yinvertiert[10-i]=ybeschriftung[i];
			}
			
			DecimalFormat y = new DecimalFormat();
			
			for (int i=0;i<11;i++)
			{
				if(yinvertiert[i]!=0)
				{
				g.drawString(""+y.format(yinvertiert[i]), width/2+10,i*height/10+5);
				}
			}
			if(xbeschriftung[5]==0 && ybeschriftung[5]==0)
			g.drawString(""+0, width/2+10, height/2+20);
		}
		
		
		
	}
	
	
}

