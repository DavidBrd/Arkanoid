package david_nour.arcanoid;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
		
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
		
	public static final int RACKET_WIDTH = 100;//(int) (0.15f * WIDTH);
	public static final int RACKET_HEIGHT = 15;//(int) (0.025f * HEIGHT);
	public static final int OFFSET = 50;
	
	public static final int BALL_RADIUS = 15;
	//public static double BALL_SPEED = 2f;
	public static final double BRICK_HEIGHT = 15;	
	public static final double BRICK_WIDTH = 50;
	
	public static final double MAX_GAME_DIFFICULTY = 100f;
	public static double DIFFICULTY_RATE = 1.20f;
	
	public static int SCORE_BONUS = 1;
	
	public static FpsCounter fpscounter;
	
	public static MainMenu mainMenu;
	
	public static void main(String[] args) {
		/*
		Model model = new Model();
		View view = new View(model, WIDTH, HEIGHT);
		Controller controller = new Controller(model, view);
		Collision collisionThread = new Collision(model);
		
		TimerTask ballSpawn = new BallSpawn(model);
		TimerTask onFire = new OnFire();
		TimerTask difficulty = new Difficulty();
		
		Timer onFireTimer = new Timer(true);
		Timer ballSpawnTimer = new Timer(true);
		Timer difficultyTimer = new Timer(true);
		difficultyTimer.scheduleAtFixedRate(difficulty, 10_000, 10_000);
		ballSpawnTimer.scheduleAtFixedRate(ballSpawn, 10_000, 10_000);
		onFireTimer.scheduleAtFixedRate(onFire, 0, 10_000);
			
		fpscounter = new FpsCounter();	
		fpscounter.start();		
			
		view.start();
		/*
		for (Ball ball : model.getBalls()) {
			ball.start();
		}*/
		
		//model.getRacket().start();
		//collisionThread.start();		
		
		mainMenu = new MainMenu();
		//Level lvl = new Level(1,1);
		//CustomLevel c = new CustomLevel();
		
	}

}
