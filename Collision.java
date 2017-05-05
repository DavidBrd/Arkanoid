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
				double xBall = ball.positionX;
				
				String resCollision = ball.checkSideCollision(this.model.getRacket()); 
					//System.out.println(resCollision);
				switch (resCollision) {
				case "bottom":
					ball.bounceBottom();
					break;
				case "topLeft":
					ball.bounceLeft();
					break;
				case "right":
					ball.bounceRight();
					break;
				case "top":
					ball.bounceTop();
					break;
				case "collision":
					
					ball.bounceTop();
					break;
				default:
					break;
				}
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

