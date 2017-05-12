package david_nour.arcanoid;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
	private int id;
	private int gameMode;
	private Model model;
	private int nbBricksCustom;
	
	public Level(int id, int gameMode) {
		this.id = id;
		this.gameMode = gameMode;
		loadLvl(this.id, this.gameMode);
	}
	
	public Level(int gameMode, ArrayList<Brick> tab, int nbBricksCustom) {
		//this.model.setBallNumber(nbBricksCustom);
		this.nbBricksCustom = nbBricksCustom;
		this.gameMode = gameMode;
		lvlCustom(this.gameMode, tab);
	}

	private void loadLvl(int id, int gameMode) {
		switch (id) {
		case 1:
			if (gameMode==1) 
				lvl1(1);
			if (gameMode==2) 
				lvl1(2);
			break;
		case 2:
			if(gameMode == 1)
				lvl2(1);
			if(gameMode == 2)
				lvl2(2);
			break;
		}
	}
	
	private void initBricksLvl2(ArrayList<Brick> bricks) {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 5;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				this.model.incrNbBricks();
				bricks.add( new Brick( (j+i + 1) * (Main.BRICK_WIDTH + 3) - 50,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10, 3));
			}
			
		}
		System.out.println("wesh");
	}
	
	private void initBricksLvl1(ArrayList<Brick> bricks) {
		int COUNT_BLOCKS_X = 11;
		int COUNT_BLOCKS_Y = 5;
		
		for (int i = 0; i < COUNT_BLOCKS_X; ++i) {
			for (int j = 0; j < COUNT_BLOCKS_Y; ++j) {
				this.model.incrNbBricks();
				bricks.add( new Brick( (j+i + 1) * (Main.BRICK_WIDTH + 3) - 50,
						(j + 2) * (Main.BRICK_HEIGHT + 3) - 10, 1));
			}
			
		}
		System.out.println("wesh");
	}	
	
	private void lvl1(int gameMode) {
				
		this.model = new Model(gameMode);
		initBricksLvl1(model.getBricks());
		View view = new View(model, Main.WIDTH, Main.HEIGHT);
		Controller controller = new Controller(model, view);
		if(gameMode == 2) {
			Controller2 controller2 = new Controller2(model, view);
		}
		Collision collisionThread = new Collision(model);
		
		TimerTask ballSpawn = new BallSpawn(model);
		TimerTask onFire = new OnFire(model);
		TimerTask difficulty = new Difficulty();
		
		Timer onFireTimer = new Timer();
		Timer ballSpawnTimer = new Timer();
		Timer difficultyTimer = new Timer();
		
		Main.fpscounter = new FpsCounter();
		
		//Quand le jeu démarre:
		difficultyTimer.scheduleAtFixedRate(difficulty, 10_000, 3_000);
		ballSpawnTimer.scheduleAtFixedRate(ballSpawn, 10_000, 10_000);
		onFireTimer.scheduleAtFixedRate(onFire, 0, 7_000);
			
		State state = new State();
		Main.fpscounter.start();		
		state.start();
		view.start();
		
		model.getRacket().start();
		if(gameMode == 2) {
			model.getRacket2().start();
		}
		
		collisionThread.start();		
	}
	
	private void lvl2(int gameMode) {
		
		this.model = new Model(gameMode);
		initBricksLvl2(model.getBricks());
		View view = new View(model, Main.WIDTH, Main.HEIGHT);
		Controller controller = new Controller(model, view);
		if(gameMode == 2) {
			Controller2 controller2 = new Controller2(model, view);
		}
		Collision collisionThread = new Collision(model);
		
		TimerTask ballSpawn = new BallSpawn(model);
		TimerTask onFire = new OnFire(model);
		TimerTask difficulty = new Difficulty();
		
		Timer onFireTimer = new Timer();
		Timer ballSpawnTimer = new Timer();
		Timer difficultyTimer = new Timer();
		
		Main.fpscounter = new FpsCounter();
		
		//Quand le jeu démarre:
		difficultyTimer.scheduleAtFixedRate(difficulty, 10_000, 7_000);
		ballSpawnTimer.scheduleAtFixedRate(ballSpawn, 10_000, 10_000);
		onFireTimer.scheduleAtFixedRate(onFire, 0, 7_000);
			
		State state = new State();
		Main.fpscounter.start();		
		state.start();
		view.start();
		
		model.getRacket().start();
		if(gameMode == 2) {
			model.getRacket2().start();
		}
		
		collisionThread.start();		
	}
	
	private void lvlCustom(int gameMode, ArrayList<Brick> tab) {
		
		this.model = new Model(gameMode);
		this.model.setBricksNumber(this.nbBricksCustom);
		model.setBricks(tab);
		View view = new View(model, Main.WIDTH, Main.HEIGHT);
		Controller controller = new Controller(model, view);
		if(gameMode == 2) {
			Controller2 controller2 = new Controller2(model, view);
		}
		Collision collisionThread = new Collision(model);
		
		TimerTask ballSpawn = new BallSpawn(model);
		TimerTask onFire = new OnFire(model);
		TimerTask difficulty = new Difficulty();
		
		Timer onFireTimer = new Timer(true);
		Timer ballSpawnTimer = new Timer(true);
		Timer difficultyTimer = new Timer(true);
		
		Main.fpscounter = new FpsCounter();
		
		//Quand le jeu démarre:
		difficultyTimer.scheduleAtFixedRate(difficulty, 10_000, 3_000);
		ballSpawnTimer.scheduleAtFixedRate(ballSpawn, 10_000, 10_000);
		onFireTimer.scheduleAtFixedRate(onFire, 0, 7_000);
			
		State state = new State();
		Main.fpscounter.start();		
		state.start();
		view.start();
		
		model.getRacket().start();
		if(gameMode == 2) {
			model.getRacket2().start();
		}
		
		collisionThread.start();
	}
	
	
}
