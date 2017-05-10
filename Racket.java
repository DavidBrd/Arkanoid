package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.RepaintManager;

public class Racket extends Thread {
	private float positionY;
	private float positionX;
	private int width, height;
	private float maxSpeed;
	private float speed;
	private float positionCenteredX, positionCenteredY;
	private Shape collider;
	private Shape progress;
	private Color racketColor = Color.BLACK;
	
	public Racket(float positionX, float positionY, int width, int height, Color color) {		
		this.width = width;
		this.height = height;
		this.positionX = positionX; 
		this.positionY = positionY - Main.OFFSET;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);
		this.progress = new Rectangle2D.Double(this.positionX, this.positionY, 0, this.height);
		this.racketColor = color;
	}
	
	public Racket(float positionX, float positionY, int width, int height) {		
		this.width = width;
		this.height = height;
		this.positionX = positionX; 
		this.positionY = positionY - Main.OFFSET;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);
		this.progress = new Rectangle2D.Double(this.positionX, this.positionY, 0, this.height);
	}
	
	public Shape getProgressShape() {
		return this.progress;
	}
	
	public void setProgressShape(Shape shape) {
		this.progress = shape;
	}
	
	public boolean leftBlock() {return ((positionX+speed) <= 0);}
	
	public float getRightEnd() {return (this.positionCenteredX + this.width);}
	

		
	public void moveLeft() {
		if (leftBlock()) {
			speed = -10;
		} else {
			speed = 0f;
		}
	}
	
	public void moveRight() {
		if (getRightEnd() < Main.WIDTH) {
			speed = +10;
		} else {
			speed = 0f;
		}
	}
	
	public float getCenteredPositionX() {return this.positionX;} //TODO : retourne la position centrée 
	
	public float getCenteredPositionY() {return this.positionX;} //TODO : retourne la position centrée
	
	public float getPositionX() {return this.positionX;}
	
	public float getPositionY() {return this.positionY;}
	
	public float getWidth() {return this.width;}
	
	public float getHeight() {return this.height;}
	
	public float getSpeed() {return this.speed;}
	
	public float getMaxSpeed() {return this.maxSpeed;}
	
	public Shape getCollider() {return this.collider;}
	
	public void setSpeed(float speed) {this.speed = speed;}
	
	public void setPositionX(float positionX) {this.positionX = positionX;}
	
	public void setPositionY(float positionY) {this.positionY = positionY;}
	
	public void incrementSpeed(float amount) {
		this.speed += amount;
	}
	
	public void checkRestraint() {
		if ( this.positionX < 0) {this.setPositionX(0);}
		if ( this.positionX >= (Main.WIDTH - this.width)) {this.setPositionX(Main.WIDTH - this.width);}
	}
	
	
	public void run() {
		while(!Model.gameOver) {
			while(!Model.paused) {		
				checkRestraint();
				this.positionX += this.speed; // Mettre dans une fonction uopdate? 		
				try {			
					Thread.sleep(10);
					if(Model.paused2){
						wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void paintRacket(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;	
		this.collider =  new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);	
		//this.progress = new Rectangle2D.Double(this.positionX, this.positionY,  (( Model.score*10)/100) * this.width, this.height);
		g.setColor(this.racketColor);
		g.draw(collider);
		g.fill(collider);
		g.setColor(Color.GREEN);
		//g.draw(progress);
		//g.fill(progress);
	}
}
