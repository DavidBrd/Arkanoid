package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.lang.model.type.IntersectionType;

public class Ball extends Thread {
	double positionX, positionY;
	double speedX = Main.BALL_SPEED;
	double speedY = Main.BALL_SPEED;
	double size = Main.BALL_RADIUS;
	boolean active;
	private Shape collider;
	
	public Ball(int positionX, int positionY, double speedX, double speedY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		active = true;
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
	}
	
	public Ball(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		active = true;
	}
	
	public void paintBall(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.collider = new Ellipse2D.Double(this.positionX, this.positionY, Main.BALL_RADIUS, Main.BALL_RADIUS);
		g2.draw(collider);
		g2.fill(collider);
		
	}
	
	public double getSize() {return this.size;}
	
	public double getPositionX() {return this.positionX;}
	
	public double getPositionY() {return this.positionY;}
	
	public synchronized Shape getCollider() {return this.collider;}
	
	public String checkSideCollision(Racket r2) {
		/*
		Rectangle r1 = this.collider.getBounds();
		
		double dX = (r1.getX() + r1.getWidth()/2) - (r2.getX() + r2.getWidth()/2);
		double dY = (r1.getY() + r1.getHeight()/2) - (r2.getX() + r2.getHeight()/2);
		
		double width = (r1.getWidth() + r2.getWidth())/2;
		double height = (r1.getHeight() + r2.getHeight())/2;
		
		double crossWidth = width * dY;
		double crossHeight = height * dX;
		
		String collision="no collision";
		
		if (Math.abs(dX) <= width && Math.abs(dY) <= height) {
			if (crossWidth > crossHeight) {
				collision=(crossWidth>(-crossHeight))?"bottom":"left";
			} else {
	            collision=(crossWidth>-(crossHeight))?"right":"top";
	        }
	    }
		
		return(collision);*/
		/*if ( ( (this.getPositionX() + this.getSize()/2) <= (r2.getPositionX() + 20) )
				|| (  (this.getPositionX() + this.getSize()/2)>(r2.getWidth() - 20))) {
				System.out.println("COIN");
				return ""
			}*/
		
		if(this.collider.getBounds().intersects(r2.getShape().getBounds())){
			if ((this.getPositionX() + this.getSize()/2) <= (r2.getPositionX() + 20)
				&& (this.speedX >=0)) 
			{
				System.out.println("COIN EFFECTIF on inverse");
				return "topLeft";	
			}
		} else {return "no collision";}
		return "collision";
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
			speedY = -Main.BALL_SPEED;
			positionX = 400; //Positon de r�aparition de la balle
			positionY = 300;
			System.out.println("- 1 vie");
		}
	}
	
	public void bounceBottom() {
		this.speedY = -Main.BALL_SPEED;
	}
	
	//La balle repart dans la direction inverse lors de la collision cot� gauche (eventuellement � changer)
	public void bounceLeft() {
		this.speedX = -Main.BALL_SPEED;
		this.speedY = -Main.BALL_SPEED;
	}
	
	public void bounceRight() {
		this.speedX = -Main.BALL_SPEED;
		this.speedY = -Main.BALL_SPEED;
	}
	
	public void bounceTop() {
		this.speedY = -Main.BALL_SPEED;
	}
	
	public void run() {
		while(true){
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
