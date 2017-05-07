package david_nour.arcanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends Thread{
	private JFrame frame;
	private Model model;
	private JLabel score;
	
	
	public View(Model model, int width, int height) {
		
		this.frame = new JFrame("TeteContreBrique");
		this.model = model;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 750);
		frame.setResizable(false);
		//frame.setLocationRelativeTo(null);
		Container cp = frame.getContentPane();
		
		JPanel rightPanel = new JPanel();
		this.score = new JLabel("Score : 0");
		score.setForeground(Color.blue);
		score.setHorizontalAlignment(JLabel.CENTER);
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(score, BorderLayout.NORTH);
		rightPanel.setPreferredSize(new Dimension(800,150));
		cp.add(rightPanel, BorderLayout.NORTH);
		rightPanel.setBackground(Color.BLACK);
		
		DisplayView display = new DisplayView(this.model);
		display.setSize( new Dimension(800, 600));
		//display.setLocation(0, 0);
		display.setBackground(Color.RED);
		cp.add(display);
			
		frame.setVisible(true);
		
	}
	
	public void run() {
		while(!Model.gameOver) {
			synchronized (this) {
				this.frame.setTitle("Arkakanoid FPS : "+Main.fpscounter.getFps());
				this.score.setText("Score : " + this.model.score);
			}				
			this.frame.repaint();	
			Main.fpscounter.interrupt();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
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
			for (Ball ball : model.getBalls()) {
				ball.paintBall(g);
			}
			
			
			for (Brick brick : model.getBricks()) {
				if(brick.getTapToDeath() > 0) {
					brick.paintBrick(g);
				}
			}
			//model.clearBricks();
		}
	}
