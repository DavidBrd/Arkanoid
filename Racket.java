package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.RepaintManager;

public class Racket extends Thread {
	private float positionY;
	private float positionX;
	private int width, height;
	private float maxSpeed;
	private float speed;
	private float positionCenteredX, positionCenteredY;
	
	public Racket(float positionX, float positionY, int width, int height) {		
		this.width = width;
		this.height = height;
		this.positionX = positionX; 
		this.positionY = positionY - Main.OFFSET;				
	}
	
	//TODO : a déplacer dans le controller
	private void calibrerPosition(int racketWidth, int racketHeight) {
		this.positionX = this.positionX + Main.WIDTH/2;
		this.positionY = this.positionY + Main.HEIGHT;
	}
	
	
	public float getCenteredPositionX() {return this.positionX;} //TODO : retourne la position centrée 
	
	public float getCenteredPositionY() {return this.positionX;} //TODO : retourne la position centrée
	
	public float getPositionX() {return this.positionX;}
	
	public float getPositionY() {return this.positionY;}
	
	public float getSpeed() {return this.speed;}
	
	public float getMaxSpeed() {return this.maxSpeed;}
	
	public void setSpeed(float speed) {this.speed = speed;}
	
	public void setPositionX(float positionX) {this.positionX = positionX;}
	
	public void setPositionY(float positionY) {this.positionY = positionY;}
	
	public void incrementSpeed(float amount) {
		this.speed += amount;
	}
	
	public void run() {
		while(true) {
			this.positionX += this.speed;			
			try {
				//System.out.println("Thread fonctinne");
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void paintRacket(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;	
		
		g.fillRect((int) this.positionX, (int) this.positionY, this.width, this.height);
		g.setColor(Color.RED);
		g.fillRect(0, 600, 400, 300);
	}
}
