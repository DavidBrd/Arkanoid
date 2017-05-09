package david_nour.arcanoid;

import java.util.Timer;
import java.util.TimerTask;

public class Level {
	private int id;
	private int gameMode;
	
	public Level(int id, int gameMode) {
		this.id = id;
		this.gameMode = gameMode;
		loadLvl(this.id, this.gameMode);
	}
	
	private void loadLvl(int id, int gameMode) {
		switch (id) {
		case 1:
			if (gameMode==1) 
				lvl1(1);
			if (gameMode==2) 
				lvl1(2);
			break;
		default:
			break;
		}
	}
	
	private void lvl1(int gameMode) {
				
		Model model = new Model(gameMode);
		View view = new View(model, Main.WIDTH, Main.HEIGHT);
		Controller controller = new Controller(model, view);
		Collision collisionThread = new Collision(model);
		
		TimerTask ballSpawn = new BallSpawn(model);
		TimerTask onFire = new OnFire();
		TimerTask difficulty = new Difficulty();
		
		Timer onFireTimer = new Timer(true);
		Timer ballSpawnTimer = new Timer(true);
		Timer difficultyTimer = new Timer(true);
		
		Main.fpscounter = new FpsCounter();
		
		//Quand le jeu démarre:
		difficultyTimer.scheduleAtFixedRate(difficulty, 10_000, 10_000);
		ballSpawnTimer.scheduleAtFixedRate(ballSpawn, 10_000, 10_000);
		onFireTimer.scheduleAtFixedRate(onFire, 0, 10_000);
			
		State state = new State();
		Main.fpscounter.start();		
		state.start();
		view.start();
		for (Ball ball : model.getBalls()) {
			ball.start();
		}		
		
		model.getRacket().start();
		if(gameMode == 2) {
			model.getRacket2().start();
		}
		
		collisionThread.start();	
		
	}
	
	
}
