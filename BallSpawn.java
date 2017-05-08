package david_nour.arcanoid;

import java.util.TimerTask;

public class BallSpawn extends TimerTask{
	private Model model;
	
	public BallSpawn(Model model) {
		this.model = model;
	}
	
	@Override
	public void run() {
		if(!Model.gameOver) {
			if(!Model.paused) {
				if (Ball.nbBall <= 30) {
					model.addBall(new Ball(400, 300, Main.BALL_SPEED, -Main.BALL_SPEED));
				}
				if (Ball.nbBall == 30) {
					this.cancel();
				}
			}
		}
	}	
}
