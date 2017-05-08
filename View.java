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
	private JLabel ballesRestantes;
	private JLabel scoreBonus;
	private HighScoreManager highScoreManager;
	private JLabel highScore;
		
	public View(Model model, int width, int height) {
		
		this.frame = new JFrame("TeteContreBrique");
		this.model = model;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 750);
		frame.setResizable(false);
		Container cp = frame.getContentPane();
		
		JPanel topPanel = new JPanel();
		this.score = new JLabel("Score : 0 ");
		score.setFont(new Font("courrier", Font.BOLD, 40));
		score.setForeground(Color.RED);
		
		
		ballesRestantes = new JLabel(" Balles : " + Ball.nbBall);
		ballesRestantes.setFont(new Font("courrier", Font.BOLD, 40));
		ballesRestantes.setForeground(Color.RED);
		
		scoreBonus = new JLabel(" X"+Main.SCORE_BONUS);
		scoreBonus.setFont(new Font("courrier", Font.BOLD, 40));
		scoreBonus.setForeground(Color.RED);
		
		highScoreManager = new HighScoreManager();
		highScoreManager.loadScoreFile();
		
		highScore = new JLabel (highScoreManager.getHighscoreString());
		highScore.setFont(new Font("courrier", Font.BOLD, 20));
		highScore.setForeground(Color.ORANGE);
				
		topPanel.setLayout(new FlowLayout());
		topPanel.add(score);	
		topPanel.add(scoreBonus);
		topPanel.add(ballesRestantes);
		topPanel.add(highScore);
		topPanel.setPreferredSize(new Dimension(800,150));
		cp.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.GRAY);
				
		DisplayView display = new DisplayView(this.model);
		display.setSize( new Dimension(800, 600));
		display.setBackground(Color.RED);
		cp.add(display);
			
		frame.setVisible(true);	
	}
	
	public void run() {
		while(!Model.gameOver) {
			while(!Model.paused) {
				synchronized (this) {
					if (Ball.nbBall <= 0) {
						highScoreManager.addScore(System.getProperty("user.name"), Model.score);
						Model.gameOver = true;
						System.exit(0);
					}
					this.frame.setTitle("Arkakanoid FPS : "+Main.fpscounter.getFps());
					this.score.setText("Score : " + this.model.score);
					this.scoreBonus.setText(" X"+Main.SCORE_BONUS);
					this.ballesRestantes.setText(" Balles : " + Ball.nbBall);				
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
	}
	
	public JFrame getFrame() {return this.frame;}

}

	@SuppressWarnings("serial")
	class DisplayView extends JPanel {
		private Model model;
		
		public DisplayView(Model model) {this.model = model;}
			
		public void paintComponent(Graphics g) {			
			
			for (Brick brick : model.getBricks()) {
				synchronized (brick) {
					if(1==1/*brick.getTapToDeath() > 0*/) {
						brick.paintBrick(g);
					}
			model.getRacket().paintRacket(g);
			for (Ball ball : model.getBalls()) {
				ball.paintBall(g);
			}
						
			
				}
			}
		}
	}
