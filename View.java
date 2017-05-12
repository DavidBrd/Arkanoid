package david_nour.arcanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
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
	private DisplayView display;
	private JLabel gg;
	private JLabel lose;
		
	public View(Model model, int width, int height) {
		
		this.frame = new JFrame("TeteContreBrique");
		this.model = model;
		this.gg = new JLabel(new ImageIcon("img/well_done.png"));
		this.lose = new JLabel(new ImageIcon("img/game_over.jpg"));

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 750);
		frame.setResizable(false);
		Container cp = frame.getContentPane();
		
		JPanel topPanel = new JPanel();
		this.score = new JLabel("0 pts");
		score.setFont(new Font("courrier", Font.BOLD, 60));
		score.setForeground(Color.RED);
		
		
		ballesRestantes = new JLabel(" Balles : " + model.getBallNumber());
		ballesRestantes.setFont(new Font("courrier", Font.BOLD, 55));
		ballesRestantes.setForeground(Color.GREEN);
		
		scoreBonus = new JLabel(" X"+Main.SCORE_BONUS);
		scoreBonus.setFont(new Font("courrier", Font.BOLD, 55));
		scoreBonus.setForeground(Color.YELLOW);
		
		highScoreManager = new HighScoreManager();
		highScoreManager.loadScoreFile();
		
		highScore = new JLabel (highScoreManager.getHighscoreString());
		highScore.setFont(new Font("courrier", Font.BOLD, 30));
		highScore.setForeground(Color.ORANGE);
				
		topPanel.setLayout(new FlowLayout());
		topPanel.add(score);	
		topPanel.add(scoreBonus);
		topPanel.add(ballesRestantes);
		topPanel.add(highScore);
		topPanel.setPreferredSize(new Dimension(800,150));
		cp.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.GRAY);
				
		display = new DisplayView(this.model);
		display.setSize( new Dimension(800, 600));
		display.setBackground(Color.RED);
		cp.add(display);
			
		frame.setVisible(true);	
	}
	
	public void run() {
		while(!Model.gameOver) {
			if(!Model.paused) {
				synchronized (this) {
					if(this.model.getNbBricks() <= 0) {
						Model.gameOver = true;
						this.display.add(gg);
						Main.BALL_SPEED = 2f;
					}
					else if (this.model.getBallNumber() <= 0) {						
						highScoreManager.addScore(System.getProperty("user.name"), this.model.getScore());
						Model.gameOver = true;
						System.out.println("Score fin : " + this.model.getScore());
						this.display.add(lose);
						Main.BALL_SPEED = 2f;
					}
					
					this.frame.setTitle("Arkakanoid FPS : "+Main.fpscounter.getFps());
					this.score.setText(this.model.getScore() + " pts");
					this.scoreBonus.setText(" X"+Main.SCORE_BONUS);
					this.ballesRestantes.setText(" Balles : " + model.getBallNumber());				
				}				
				this.frame.repaint();	
				System.out.println("Briques  : " + this.model.getNbBricks());
				System.out.println(this.model.getBallNumber());
				Main.fpscounter.interrupt();
				try {
					Thread.sleep(10);
//					if(Model.paused2){
//						wait();
//					}
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
		private Image background;
		
		public DisplayView(Model model) {
			this.model = model;
			try {
				this.background = ImageIO.read(new File("img/background.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
			
		public synchronized void paintComponent(Graphics g) {	
		
			g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), this);
			
			for (Brick brick : model.getBricks()) {
				synchronized (brick) {
					brick.paintBrick(g);
					model.getRacket().paintRacket(g);
					if( Model.gameMode == 2) {
						model.getRacket2().paintRacket(g);
					}
					for (Ball ball : model.getBalls()) {
						ball.paintBall(g);
					}					
				}
			}
		}
	}
