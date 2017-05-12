package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
	private ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<Ball> balls = new ArrayList<>();
	private Racket racket;
	private Racket racket2;
	
	public static int gameMode;
	public static boolean gameOver = false;
	public static boolean paused = false;// Gestion eventuelle d'un système de pause
	//public static boolean paused2 = false;// Gestion eventuelle d'un système de pause
	private int score;
	private int ballNumber;
	private int nbBricks;
	private View view;
	
	public Model(int gameMode) {
		this.nbBricks = 0;
		this.ballNumber = 0;
		this.score = 0;
		this.gameOver = false;
		this.gameMode = gameMode;
		if(gameMode == 1) {
			this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH, Main.RACKET_HEIGHT);
			
		}
		
		if(gameMode == 2) { 
			this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH/2, Main.RACKET_HEIGHT/2, Color.RED);
			this.racket2 = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH/2, Main.RACKET_HEIGHT/2, Color.BLUE);
			
		}
		this.addBall(new Ball(380,580,-Main.BALL_SPEED,-Main.BALL_SPEED));
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
	
	public void updateScore() {
		this.score = this.score + 10*Main.SCORE_BONUS;
	}
	//@SuppressWarnings("static-access")
	public int getBallNumber() {return this.ballNumber;}
	
	//@SuppressWarnings("static-access")
	public void setBallNumber(int number) {this.ballNumber = number;}
	
	public void incrBallNumber() {
		this.ballNumber++;
	}
	
	public void decrBallNumber() {
		this.ballNumber--;
	}
	
	public int getNbBricks() {
		return this.nbBricks;
	}
	
	public void setBricksNumber(int n) {
		this.nbBricks = n;
	}
	
	public void incrNbBricks() {
		this.nbBricks++;
	}
	
	public void decrNbBricks() {
		this.nbBricks--;
	}
	
	public void addBall(Ball ball) {
		this.balls.add(ball);
		this.ballNumber++;
	}
	
	
	public Racket getRacket() {return this.racket;}
	public Racket getRacket2() {return this.racket2;}
	public ArrayList<Ball> getBalls() {return this.balls;}
	public ArrayList<Brick> getBricks() {return this.bricks;}
	
	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}
	
	public void clearBricks() {
		Iterator<Brick> bricksIt = bricks.iterator();
		while (bricksIt.hasNext()) {
			Brick brick = bricksIt.next();
			if (brick.getTapToDeath() <= 0) { 
				bricksIt.remove();				
			}		
		}
	}
	
	public void clearBalls() {
		Iterator<Ball> ballsIt = balls.iterator();
		while (ballsIt.hasNext()) {
			Ball ball = ballsIt.next();
			if (!ball.isActive()) { 
				ballsIt.remove();				
			}		
		}
	}	
}
