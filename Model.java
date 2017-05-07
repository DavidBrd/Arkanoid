package david_nour.arcanoid;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
	private ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<Ball> balls = new ArrayList<>();
	private Racket racket;
	public static boolean gameOver = false;
	public static boolean paused = false;
	public static int score = 0;
	public static boolean updateAll = false;
	private int ballNumber;
	private View view;
	
	public Model(Racket racket) {
		
	}
	
	public Model(int frameWidth, int frameHeight) {
		float racketWidth = (0.15f * frameWidth);
		float racketHeight = (0.02f * frameHeight);
		//System.out.println((int) racketWidth+"  "+ (int) racketHeight);
		this.racket = new Racket((Main.WIDTH/2) - (Main.RACKET_WIDTH/2), (Main.HEIGHT - Main.RACKET_HEIGHT), Main.RACKET_WIDTH, Main.RACKET_HEIGHT);
		this.balls.add(new Ball(400,-15,-Main.BALL_SPEED,-Main.BALL_SPEED));
		this.balls.add(new Ball(-10,-15,Main.BALL_SPEED,-Main.BALL_SPEED));
		this.balls.add(new Ball(200,200,-Main.BALL_SPEED,Main.BALL_SPEED));
		this.balls.add(new Ball(200,45,Main.BALL_SPEED,Main.BALL_SPEED));
		
		initBricks_lvl1();
	}
	
	public Racket getRacket() {return this.racket;}
	public ArrayList<Ball> getBalls() {return this.balls;}
	public ArrayList<Brick> getBricks() {return this.bricks;}
	public int getBallNumber() {return this.ballNumber;}
	public void setBallNumber(int number) {this.ballNumber = number;}
	
	public synchronized void clearBricks() {
		Iterator<Brick> bricksIt = bricks.iterator();
		while (bricksIt.hasNext()) {
			Brick brick = bricksIt.next();
			if (brick.getTapToDeath() <= 0) { //!brick.isActive() 
				bricksIt.remove();				
			}		
		}
	}
	
	public void initBricks_lvl1() {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 4;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				bricks.add(new Brick((j+i + 1) * (Main.BRICK_WIDTH + 3) + 85,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10));
			}
			
		}
		
		for (int i = 0; i < bricks.size(); i+=2) {
			bricks.get(i).setTapToDeath(3);
		}
	}	
	
	
	
	
}
