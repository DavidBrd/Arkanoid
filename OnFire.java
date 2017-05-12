package david_nour.arcanoid;

import java.util.TimerTask;

public class OnFire extends TimerTask{
	
	private Model model;
	
	public OnFire(Model model) {
		this.model = model;
	}
	
	public void run() {
		if(!Model.gameOver) {
				if(!Model.paused) {
					if (model.getBallNumber() >= 2) {
						Main.SCORE_BONUS *= 2 + (model.getBallNumber() - 2);
						System.out.println("AMAZING!");		
					}
					if (model.getBallNumber() < 2) {
						Main.SCORE_BONUS = 1;
						System.out.println("You lost the bonus!");
					}	
			}
		}
	}
	
	
}
