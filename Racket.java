package david_nour.arcanoid;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Racket {
	private float positionY;
	private float positionX;
	private int width, height;
	private float maxSpeed;
	private float speed;
	
	public Racket(float positionX, float positionY, int width, int height) {		
		this.width = width;
		this.height = height;
		this.positionX = positionX; 
		this.positionY = positionY;				
	}
	
	//TODO : a déplacer dans le controller
	private void calibrerPosition(int racketWidth, int racketHeight) {
		this.positionX = this.positionX + Main.WIDTH/2;
		this.positionY = this.positionY + Main.HEIGHT;
	}
	
	
	
	public float getPositionX() {return this.positionX;}
	
	public float getPositionY() {return this.positionY;}
	
	public float getSpeed() {return this.speed;}
	
	public float getMaxSpeed() {return this.maxSpeed;}
	
	public void setSpeed(float speed) {this.speed = speed;}
	
	public void setPositionX(float positionX) {this.positionX = positionX;}
	
	public void setPositionY(float positionY) {this.positionY = positionY;}
	
	public void paintRacket(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;
		
		g.fillRect((int) this.positionX, (int) this.positionY, this.width, this.height);
	}
}
