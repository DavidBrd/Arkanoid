package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball {
	private double positionX, positionY;
	private double speedX = 2;
	private double speedY = 2;
	private double size = Main.BALL_RADIUS;
	private boolean active;
	private Shape collider;
	private boolean enemy;
	
	public Ball(int positionX, int positionY, double speedX, double speedY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		active = true;
		enemy = false;
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
	}
	
	public boolean isEnemy() {
		return enemy;
	}
	
	public void setEnemy(boolean bool) {
		enemy = bool;
	}
	
	public void paintBall(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(this.enemy) {
			g2.setColor(Color.RED);
		} else {
			g2.setColor(Color.GRAY);
		}
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
		if(active) {
			g2.draw(collider);
			g2.fill(collider);
		}			
	}
	
	public void multiplySpeed(float amount) {
		this.speedX *= amount;
	}
	
	public boolean isActive() {return this.active;}
	
	public double getSize() {return this.size;}
	
	public double getPositionX() {return this.positionX;}
	
	public double getPositionY() {return this.positionY;}
	
	public synchronized Shape getCollider() {return this.collider;}
	
	private boolean ballIntersectsBrick(Brick brick) {
		synchronized (brick) {
			return this.collider.getBounds().intersects(brick.getCollider().getBounds());
		}
		
	}
	
	public synchronized String checkCollisionBrick(Brick brick) {		
		
			double xLeftBrick = brick.getPositionX();
			double xRightBrick = xLeftBrick + brick.getWidth();	
			double xBallCentered = this.positionX + size/2;
			double yBallCentered = this.positionY + size/2;
			
			if ( brick.getTapToDeath() <= 0) {return "";}
			
			if ( this.ballIntersectsBrick(brick) ) {
				if ( xLeftBrick <= xBallCentered && xBallCentered <= xRightBrick ) {
					if ( this.speedY > 0) {
						//System.out.println("[Debug] collision : top");
						return "top";
					} else {
						//System.out.println("[Debug] collision : bottom");
						return "bottom";
					}											
				} else 
					if ( xBallCentered < xLeftBrick + 5) {
					//System.out.println("[Debug] collision : left");
					return "left";
				} else if(xBallCentered > xRightBrick){
					//System.out.println("[Debug] collision : right");
					return "right";
				}
						
				
			}
			
			
			/*
			if ( this.ballIntersectsBrick(brick) ) {
				if ( xLeftBrick <= this.positionX && this.positionX <= xRightBrick ) {
					if ( this.speedY > 0) {
						return "top";
					} else {
						return "bottom";
					}
				} else if ( this.speedX > 0 ) {
					return "left";
				} else {
					return "right";
				}								
			}
			*/
			return "no collision";
		
	}	
	
	private boolean ballIntersectsRacket(Racket racket) {
		return this.collider.getBounds().intersects(racket.getCollider().getBounds());
	}
	
	public String checkSideCollision(Racket racket) {					
		synchronized (racket) {			
			if(this.ballIntersectsRacket(racket) ) {				
				
				if(!racket.isActive()) {
					return "no collision";
				}
				if (racket.isActive() && (this.getPositionX() + this.getSize()/2) <= (racket.getPositionX() + 20/Model.gameMode) && ( this.speedX >= 0 ) ) {				
					return "topLeft";	
				}				
				if (racket.isActive() && (this.getPositionX() + this.getSize()/2) > ( (racket.getPositionX()+racket.getWidth()) - 20/Model.gameMode ) && ( this.speedX < 0 ) )  {
					return "topRight";
				} 				
				return "top";
			} 					
			return "no collision";
		}
	}
		
	
	public synchronized void update(Model model) {
		positionX += speedX;
		positionY += speedY;
	
		if ( positionX < 0)
			speedX = model.getBallSpeed();//Main.BALL_SPEED;
		else if ( (positionX + size) > Main.WIDTH)
			speedX = -model.getBallSpeed();//Main.BALL_SPEED;
		if ( positionY < 0) {
			speedY = model.getBallSpeed();//Main.BALL_SPEED;
		} else if ( (positionY + size) > Main.HEIGHT) {
			//System.out.println("[Debug] BALL PERDUE" + model.getBallNumber());
			model.decrBallNumber();
			active = false;				
			//Model.gameOver = true;
			//System.out.println("Balle perdue");
			//speedY = -Main.BALL_SPEED;
			//positionX = 400; //Positon de réaparition de la balle
			//positionY = 300;
			
		}
	}
	
	public void bounceBottom(Model model) {
		this.speedY = model.getBallSpeed();
	}
		
	public void bounceTopLeft(Model model) {
		this.speedX = -model.getBallSpeed();
		this.speedY = -model.getBallSpeed();
	}
	
	public void bounceTopRight(Model model) {
		this.speedX =  model.getBallSpeed();
		this.speedY = -model.getBallSpeed();
	}
	
	public void bounceLeft(Model model) {
		this.speedX = -model.getBallSpeed();
	}
	
	public void bounceRight(Model model) {
		this.speedX = model.getBallSpeed();
	}
	
	public void bounceTop(Model model) {
		this.speedY = -model.getBallSpeed();
	}
}
