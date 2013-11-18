package View;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.SwingConstants;

import model.FktBerechnungen;
import model.Token;


public class FktFlaeche extends JPanel {
	private double ymax;
	private double ymin;
	private double xmax;
	private double xmin;
	private double[][] punkte;

// TODO Paint Component Methode Verändern	
	public void paintComponent(Graphics g)
	{
		vorbereitung(g);
		/*
		g.setColor(Color.RED);
		
		
		
		// new 
		FktBerechnungen f= new FktBerechnungen();
		
		
		int[] xPoints= new int[60];
		int[] yPoints= new int[60];
		
		List<Token> liste = f.tokenize("1");
		List<Token> zwei = f.infixNachUpn(liste);	

		int a= -10;
		int b= Math.abs(a)+50;
		for(int i=0; i<b;i++)
		{	
			List<Token> drei= f.ersetzeX(a, zwei);
			xPoints[i]=a;
			yPoints[i]=(int)f.upnNachDouble(drei);
			a++;
		}
		// new end

		for(int i=0;i<yPoints.length;i++)
		{
			yPoints[i]=-yPoints[i];
		}
		int nPoints=xPoints.length;
		
		int xdifferenz=Math.abs(xmax)+Math.abs(xmin);
		int ydifferenz=Math.abs(ymax)+Math.abs(ymin);
		
		int[] xPixel=new int[xPoints.length];
		int[] yPixel=new int[yPoints.length];
		
		for(int i=0; i<xPixel.length;i++)
		{
			xPixel[i]=(super.getWidth()*xPoints[i])/xdifferenz;
		}
		
		for(int i=0; i<yPixel.length;i++)
		{
			yPixel[i]=(super.getHeight()*yPoints[i])/ydifferenz;
		}
		
		g.drawPolyline(xPixel, yPixel, nPoints);
		
		
		
	*/
	}
	
	public void setMinMax(double yMax,double yMin, double xMax, double xMin)
	{
		ymax=yMax;
		ymin=yMin;
		xmin=xMin;
		xmax=xMax;
	}

	public void setPunkte(double[][] pkt)
	{
		punkte=pkt;
	}
	
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
	
	public void beschriftung(Graphics g)
	{
		int width= super.getWidth()-20;
		int height= super.getHeight()-20;
		
		if (Double.valueOf(xmin)!=null && Double.valueOf(xmax)!=null && Double.valueOf(ymin)!=null && Double.valueOf(ymax) != null)
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

