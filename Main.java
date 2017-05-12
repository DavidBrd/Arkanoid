package david_nour.arcanoid;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
		
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
		
	public static final int RACKET_WIDTH = 100;
	public static final int RACKET_HEIGHT = 15;
	public static final int OFFSET = 50;
	
	public static final int BALL_RADIUS = 15;
	public static double BALL_SPEED = 2f;
	public static final double BRICK_HEIGHT = 15;	
	public static final double BRICK_WIDTH = 50;
	
	public static final double MAX_GAME_DIFFICULTY = 8;
	public static final double DIFFICULTY_RATE = 1.05f;
	
	public static int SCORE_BONUS = 1;
	
	public static FpsCounter fpscounter;
	
	public static MainMenu mainMenu;
	
	public static void main(String[] args) {	
		mainMenu = new MainMenu();		
	}

}
