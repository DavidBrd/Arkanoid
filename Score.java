package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Score extends Thread{
	public final int POINTS_PER_BRICK = 10;
	
	private JPanel panel;
	private int score = 0;
	
	public Score(JPanel panel) {
		this.panel = panel;
	}
	
	public int getScore() {
		return score;
	}
	
	public void add(int points) {
		score += points;	
	}
	
	public void paintScore(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;
		g.setColor(Color.RED);
		g.setFont(new Font("Courrier", 0, 20));
		g.drawString("Score "+score, 50, 50);		
	}
	
	public void run() {
		while(!Model.gameOver) {
			paintScore(panel.getGraphics());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
