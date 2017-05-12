package david_nour.arcanoid;

import java.util.TimerTask;

public class Difficulty extends TimerTask{
	private Model model;
	public Difficulty(Model model) {
		this.model = model;
	}
	
	public void run() {
		if(!Model.gameOver) {
			if(!Model.paused) {
				//double speed = model.getBallSpeed();
				if (model.getBallSpeed() <= Main.MAX_GAME_DIFFICULTY) {
					model.setBallSpeed(model.getBallSpeed() * Main.DIFFICULTY_RATE);
					System.out.println("Speed up");
					}
			}
		}		
	}
}
