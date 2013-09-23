import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;


public class FktFlaeche extends JPanel {
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.GRAY);
		g.drawLine(0,super.getHeight()/2, super.getWidth(), super.getHeight()/2); // X-Achse
		g.drawLine(super.getWidth()/2,0, super.getWidth()/2, super.getHeight()); // Y-Achse
		
		for(int i=0
				;i<10;i++)
		{
			g.drawLine(i*(super.getWidth()/10), (super.getHeight()/2)-10,i*(super.getWidth()/10),(super.getHeight()/2)+10);
		}
		g.translate(super.getSize().width / 2, super.getSize().height / 2);

		g.setColor(Color.RED);

		int[] xPoints={-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10};
		int[] yPoints={100,81,64,49,36,25,16,9,4,1,0,1,4,9,16,25,36,49,64,81,100};
		
		for(int i=0;i<yPoints.length;i++)
		{
			yPoints[i]=-yPoints[i];
		}
		int nPoints=xPoints.length;
		
		int xmin=-10;
		int xmax=10;
		int ymin=-10;
		int ymax=10;
		
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
		
		
		
	
	}
	

}
