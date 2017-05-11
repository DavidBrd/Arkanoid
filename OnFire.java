package david_nour.arcanoid;

import java.util.TimerTask;

public class OnFire extends TimerTask{
	
	public void run() {
		if(!Model.gameOver) {
				if(!Model.paused) {
					if (Ball.nbBall >= 2) {
						Main.SCORE_BONUS *= 2 + (Ball.nbBall - 2);
						System.out.println("AMAZING!");		
					}
					if (Ball.nbBall < 2) {
						Main.SCORE_BONUS = 1;
						System.out.println("You lost the bonus!");
					}	
			}
		}
		this.cancel();
	}
	
	
}
