package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball extends Thread {
	double positionX, positionY;
	double speedX = Main.BALL_SPEED;
	double speedY = Main.BALL_SPEED;
	double size = Main.BALL_RADIUS;
	boolean active;
	private Shape collider;
	public static int nbBall = 0;
	
	public Ball(int positionX, int positionY, double speedX, double speedY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		active = true;
		nbBall++;
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
	}
	
	public Ball(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		active = true;
	}
	
	public void paintBall(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
		g2.draw(collider);
		g2.fill(collider);
		
	}
	
	public double getSize() {return this.size;}
	
	public double getPositionX() {return this.positionX;}
	
	public double getPositionY() {return this.positionY;}
	
	public synchronized Shape getCollider() {return this.collider;}
	
	private boolean ballIntersectsBrick(Brick brick) {
		return this.collider.getBounds().intersects(brick.getCollider().getBounds());
	}
	
	public String checkCollisionBrick(Brick brick) {		
		double xLeftBrick = brick.getPositionX();
		double xRightBrick = xLeftBrick + brick.getWidth();	
		
		if ( this.ballIntersectsBrick(brick) ) {
			if ( xLeftBrick < this.positionX && this.positionX < xRightBrick ) {
				if ( this.speedX > 0) {
					return "top";
				} else {
					return "bottom";
				}
			} else {
				if ( this.speedX > 0 ) {
					return "left";
				} else {
					return "right";
				}
			}
		}
		return "no collision";
	}	
	
	private boolean ballIntersectsRacket(Racket racket) {
		return this.collider.getBounds().intersects(racket.getCollider().getBounds());
	}
	
	public String checkSideCollision(Racket racket) {					
		
		if( this.ballIntersectsRacket(racket) ) {
			
			if ( (this.getPositionX() + this.getSize()/2) <= (racket.getPositionX() + 20) && ( this.speedX >= 0 ) ) {				
				return "topLeft";	
			}
			
			if ( (this.getPositionX() + this.getSize()/2) > ( (racket.getPositionX()+racket.getWidth()) - 20 ) && ( this.speedX < 0 ) )  {
				return "topRight";
			} 
			
			return "top";
		} 	
			
		return "no collision";
	}
	
	
	
	public synchronized void update() {
		positionX += speedX;
		positionY += speedY;
	
		if ( positionX < 0)
			speedX = Main.BALL_SPEED;
		else if ( (positionX + size) > Main.WIDTH)
			speedX = -Main.BALL_SPEED;
		if ( positionY < 0) {
			speedY = Main.BALL_SPEED;
		} else if ( (positionY + size) > Main.HEIGHT) {
			//speedY = -Main.BALL_SPEED;
			//positionX = 400; //Positon de réaparition de la balle
			//positionY = 300;
			
		}
	}
	
	public void bounceBottom() {
		this.speedY = -Main.BALL_SPEED;
	}
	
	//La balle repart dans la direction inverse lors de la collision coté gauche (eventuellement à changer)
	public void bounceLeft() {
		this.speedX = -Main.BALL_SPEED;
		this.speedY = -Main.BALL_SPEED;
	}
	
	public void bounceRight() {
		this.speedX = Main.BALL_SPEED;
		this.speedY = Main.BALL_SPEED;
	}
	
	public void bounceTop() {
		this.speedY = -Main.BALL_SPEED;
	}
	
	public void run() {
		while(!Model.paused){
			update();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
