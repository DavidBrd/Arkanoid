package david_nour.arcanoid;

import java.util.TimerTask;

public class Difficulty extends TimerTask{
	
	public void run() {
		if(!Model.gameOver) {
			if(!Model.paused) {
				if (Main.BALL_SPEED <= Main.MAX_GAME_DIFFICULTY) {
					Main.BALL_SPEED *= Main.DIFFICULTY_RATE;
				}
			}
		}		
	}
}
