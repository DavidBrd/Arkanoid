package david_nour.arcanoid;

import java.awt.Rectangle;

public class Collision extends Thread{
	
	private Model model;
	
	public Collision(Model model) {
		this.model = model;
	}
	
	public void run() {
		while(true){
			for (Ball ball : this.model.getBalls()) {
				ball.checkSideCollision(this.model.getRacket().getShape().getBounds());
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
