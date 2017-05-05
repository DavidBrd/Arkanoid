package david_nour.arcanoid;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private Brick[] bricks;
	private ArrayList<Ball> balls = new ArrayList<>();
	private Racket racket;
	private int score;
	private int ballNumber;
	private View view;
	
	public Model(Racket racket) {
		
	}
	
	public Model(int frameWidth, int frameHeight) {
		float racketWidth = (0.15f * frameWidth);
		float racketHeight = (0.02f * frameHeight);
		//System.out.println((int) racketWidth+"  "+ (int) racketHeight);
		this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH, Main.RACKET_HEIGHT);
		this.balls.add(new Ball(-10, -10));
		this.balls.add(new Ball(400,-15,-Main.BALL_SPEED,-Main.BALL_SPEED));
	}
	
	public Racket getRacket() {return this.racket;}
	public ArrayList<Ball> getBalls() {return this.balls;}
	
	
	
	
	
}
