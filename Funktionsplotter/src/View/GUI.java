package View;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {

	private JFrame frame;
	private JTextField TextFFkt;
	private JTextField xminT;
	private JTextField xmaxT;
	private JTextField yminT;
	private JTextField ymaxT;
	private FktFlaeche Zeichenflaeche;
	private JButton Zeichnen;
	private JButton Loeschen;
	private JButton zoomin = new JButton("+");
	private JButton zoomout = new JButton("-");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

				
					GUI window = new GUI();
					window.frame.setVisible(true);
				
			}


	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 744, 582);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Zeichenflaeche = new FktFlaeche();
		Zeichenflaeche.setBounds(8, 19, 520, 520);
		frame.getContentPane().add(Zeichenflaeche);
		Zeichenflaeche.setLayout(null);
		
		int width=20;
		
		TextFFkt = new JTextField();
		TextFFkt.setBounds(555+width, 86, 134, 28);
		frame.getContentPane().add(TextFFkt);
		TextFFkt.setColumns(10);
		
		JLabel FktGleichung = new JLabel("Funktionsgleichung");
		FktGleichung.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		FktGleichung.setBounds(565+width, 55, 134, 22);
		frame.getContentPane().add(FktGleichung);
		
		JLabel y = new JLabel("y=");
		y.setBounds(533+width, 92, 43, 16);
		frame.getContentPane().add(y);
		
		JLabel lblKoordinatensystem = new JLabel("Koordinatensystem");
		lblKoordinatensystem.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblKoordinatensystem.setBounds(565+width, 138, 123, 28);
		frame.getContentPane().add(lblKoordinatensystem);
		
		xminT = new JTextField();
		xminT.setText("-5");
		xminT.setBounds(555+width, 178, 134, 28);
		frame.getContentPane().add(xminT);
		xminT.setColumns(10);
		
		xmaxT = new JTextField();
		xmaxT.setText("5");
		xmaxT.setBounds(555+width, 218, 134, 28);
		frame.getContentPane().add(xmaxT);
		xmaxT.setColumns(10);
		
		yminT = new JTextField();
		yminT.setText("-5");
		yminT.setBounds(555+width, 261, 134, 28);
		frame.getContentPane().add(yminT);
		yminT.setColumns(10);
		
		ymaxT = new JTextField();
		ymaxT.setText("5");
		ymaxT.setBounds(555+width, 301, 134, 28);
		frame.getContentPane().add(ymaxT);
		ymaxT.setColumns(10);
		
		Zeichnen = new JButton("Zeichnen");
		Zeichnen.setBounds(560+width, 341, 117, 29);
		frame.getContentPane().add(Zeichnen);
		
		Loeschen = new JButton("L\u00F6schen");
		Loeschen.setBounds(560+width, 382, 117, 29);
		frame.getContentPane().add(Loeschen);
		
		JLabel lblXmin = new JLabel("Xmin");
		lblXmin.setBounds(515+width, 184, 61, 16);
		frame.getContentPane().add(lblXmin);
		
		JLabel lblXmax = new JLabel("Xmax");
		lblXmax.setBounds(515+width, 224, 61, 16);
		frame.getContentPane().add(lblXmax);
		
		JLabel lblYmin = new JLabel("Ymin");
		lblYmin.setBounds(515+width, 267, 61, 16);
		frame.getContentPane().add(lblYmin);
		
		JLabel lblYmax = new JLabel("Ymax");
		lblYmax.setBounds(515+width, 307, 61, 16);
		frame.getContentPane().add(lblYmax);
		
		// JButton zoomin = new JButton("+");
		zoomin.setBounds(585, 423, 44, 22);
		frame.getContentPane().add(zoomin);
		
		// JButton zoomout = new JButton("-");
		zoomout.setBounds(648, 423, 44, 22);
		frame.getContentPane().add(zoomout);
		
	
		
	}
	
	public double getXmin()
	{
		return Double.parseDouble(xminT.getText());
	}
	
	public double getXmax()
	{
		return Double.parseDouble(xmaxT.getText());
	}
	
	public double getYmin()
	{
		return Double.parseDouble(yminT.getText());
	}
	public double getYmax()
	{
		return Double.parseDouble(ymaxT.getText());
	}
	public String getFunction()
	{
		return TextFFkt.getText();
	}
	
	public void addZeichenListener(ActionListener zeichnenlistener)
	{
		Zeichnen.addActionListener(zeichnenlistener);
	}
	
	public void addLoeschenListener(ActionListener loeschenlistener)
	{
		Loeschen.addActionListener(loeschenlistener);
	}
	
	public void addZoomInListener(ActionListener zoominlistener)
	{
		zoomin.addActionListener(zoominlistener);
	}
	
	public void addZoomOutListener(ActionListener zoomoutlistener)
	{
		zoomout.addActionListener(zoomoutlistener);
	}
	
	public void displayErrorMessage(String errorMessage)
	{	       	
        JOptionPane.showMessageDialog(this, errorMessage);      
	}
	public FktFlaeche getZeichenflaeche()
	{
		return Zeichenflaeche;
	}
	
	public JTextField getXmaxButton()
	{
		return xmaxT;
	}
	public JTextField getXminButton()
	{
		return xminT;
	}
	public JTextField getYmaxButton()
	{
		return ymaxT;
	}
	public JTextField getYminButton()
	{
		return yminT;
	}
}
