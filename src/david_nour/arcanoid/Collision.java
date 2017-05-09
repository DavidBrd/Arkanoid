package david_nour.arcanoid;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Collision extends Thread{
	
	private Model model;
	
	public Collision(Model model) {
		this.model = model;
	}
	
	private void ballRacketCollisions(Racket racket){
		synchronized (this.model.getRacket()) {
			for (Ball ball : this.model.getBalls()) {		
				String resCollision = ball.checkSideCollision(racket); 
				
				switch (resCollision) {
				case "topRight":					
					ball.bounceTopRight();
					if (racket.getSpeed()!=0) {
						ball.multiplySpeed(1.2f);
					}	
					break;
				case "topLeft":
					ball.bounceTopLeft();
					if (racket.getSpeed()!=0) {
						ball.multiplySpeed(1.2f);
					}	
					break;
				case "top":
					ball.bounceTop();
					if (racket.getSpeed()!=0) {
						ball.multiplySpeed(1.2f);
					}	
					break;
				default:
					break;
				}
			}
		}
	}
	
	
	
	private synchronized void ballBrickCollisions()  {
		
		for (Ball ball : this.model.getBalls()) {
			for (Brick brick : this.model.getBricks()) {
				//synchronized (brick) {									
					if(brick.getTapToDeath() > 0) {
					
						String resCollision = ball.checkCollisionBrick(brick);
					
						switch (resCollision) {
						case "top":					
							ball.bounceTop();							
							brick.setTapToDeath(brick.getTapToDeath()-1);						
							if (brick.getTapToDeath() <= 0) {Model.score+=10*Main.SCORE_BONUS;}
							break;
						case "bottom":
							ball.bounceBottom();
							brick.setTapToDeath(brick.getTapToDeath()-1);
							if (brick.getTapToDeath() <= 0) {Model.score+=10*Main.SCORE_BONUS;}
							break;
						case "left":
							ball.bounceLeft();
							brick.setTapToDeath(brick.getTapToDeath()-1);
							if (brick.getTapToDeath() <= 0) {Model.score+=10*Main.SCORE_BONUS;}
							break;
						case "right":
							ball.bounceRight();
							brick.setTapToDeath(brick.getTapToDeath()-1);
							if (brick.getTapToDeath() <= 0) {Model.score+=10*Main.SCORE_BONUS;}
							break;
						case "no collision":
							break;
						default:
							break;
						}		
					//}				
				}		
			} 
		}	
	}
	
	
	public void run() {
		
		while(!Model.gameOver){			
			
			while(!Model.paused) {
				ballRacketCollisions(model.getRacket());	
				ballRacketCollisions(model.getRacket2());
				ballBrickCollisions();
				for (Ball ball : model.getBalls()) {
					if (ball.isActive()) {
						ball.update();
					}					
				}	
				try {				
					Thread.sleep(10);
					if(Model.paused2) {
						wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

