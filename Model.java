package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	private ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<Ball> balls = new ArrayList<>();
	private ArrayList<Bonus> bonus = new ArrayList<>();
	private Racket racket;
	private Racket racket2;
	private double ballSpeed =3f;
	
	
	public static int gameMode;
	public static boolean gameOver = false;
	public static boolean paused = false;
	public static boolean paused2 = false;// A utiliser en attendant de nettoyer le code.
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
		this.ballSpeed = 3f;
		this.addBall(new Ball(380,580,-this.ballSpeed,-this.ballSpeed));
		System.out.println("added first ball");
		this.ballSpeed = 3f;
		
	}
	
	public void addBonus(Bonus bonus) {
		this.bonus.add(bonus);
	}
	
	public double getBallSpeed() {
		return this.ballSpeed;
	}
	
	public void setBallSpeed(double speed) {
		this.ballSpeed = speed;
	}
	
	public ArrayList<Bonus> getBonus() {
		return this.bonus;
	}
	public void paintBonus(Graphics g) {
		for (Bonus bonus : this.bonus) {
			bonus.paintBonus(g);
		}
	}
	
	public void updateBonus() {
		for (Bonus bonus : this.bonus) {
			if(bonus.isActive()) {
				//System.out.println("Bonus update");
				bonus.update();
			}
		}
	}
	
	public Model() {
		this.gameMode = gameMode;
		this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH, Main.RACKET_HEIGHT);
			
		
		this.balls.add(new Ball(380,580,-this.ballSpeed,-this.ballSpeed));
		
		//this.balls.add(new Ball(420,580,Main.BALL_SPEED,-Main.BALL_SPEED));
		//this.balls.add(new Ball(0,550,Main.BALL_SPEED,Main.BALL_SPEED));
		//this.balls.add(new Ball(200,45,Main.BALL_SPEED,Main.BALL_SPEED));		
		//initBricks_lvl0();
		//initBricks_lvl1();
		initBricks();
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
	
	public void initBricks() {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 10;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				bricks.add(new Brick((j+i + 1) * (Main.BRICK_WIDTH + 3) - 30,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10));
			}
			
		}
						
		for (int i = 0; i < bricks.size(); i+=2) {
			bricks.get(i).setTapToDeath(3);
		}
	}	
	
	public void initBricks_lvl2() {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 5;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				bricks.add( new Brick( (j+i + 1) * (Main.BRICK_WIDTH + 3) - 50,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10, 3));
			}
			
		}
	}		
	
	public void initBricks_lvl0() {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 4;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				bricks.add( new Brick( (j+i + 1) * (Main.BRICK_WIDTH + 3) - 30,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10, 1));
			}
			
		}
	}	
}
