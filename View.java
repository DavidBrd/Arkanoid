package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends Thread{
	private JFrame frame;
	private Model model;
	
	public View(Model model, int width, int height) {
		
		this.frame = new JFrame("Arcacanoid");
		this.model = model;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		
		Container cp = frame.getContentPane();
		
		DisplayView display = new DisplayView(this.model);
		display.setSize( new Dimension(800, 600));
		display.setBackground(Color.RED);
		cp.add(display);
		
		frame.setVisible(true);
		
	}
	
	public void run() {
		while(true) {
			this.frame.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public JFrame getFrame() {return this.frame;}

}

	class DisplayView extends JPanel {
		private Model model;
		
		public DisplayView(Model model) {this.model = model;}
		
		public void paintComponent(Graphics g) {			
			model.getRacket().paintRacket(g);
		}
	}
