package david_nour.arcanoid;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {
	private JFrame frame;
	private Model model;
	
	public View(Model model, int width, int height) {
		
		this.frame = new JFrame("Arcacanoid");
		this.model = model;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		
		Container cp = frame.getContentPane();
		
		
		DisplayView display = new DisplayView(this.model);
		cp.add(display);
		
		frame.setVisible(true);
		
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
