package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;

import javax.lang.model.type.IntersectionType;

public class Ball extends Thread {
	double positionX, positionY;
	double speedX = Main.BALL_SPEED;
	double speedY = Main.BALL_SPEED;
	double size = Main.BALL_RADIUS;
	boolean active;
	
	public Ball(int positionX, int positionY, double speedX, double speedY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
		active = true;
	}
	
	public Ball(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		active = true;
	}
	
	public void paintBall(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) positionX, (int) positionY, (int) size, (int) size); 
		
	}
	
	
	//TODO : intersect
	public boolean checkBrickCollision(Ball ball, Brick brick) {
		return true;
	}
	
	//TODO : a mettre dans model?
	public boolean checkRacketCollision(Ball ball, Racket racket) {
		if (1==2 /*mettre intersect*/) {			
			ball.speedY = -Main.BALL_SPEED;
			if (ball.positionX < racket.getPositionX())
				ball.speedX = -Main.BALL_SPEED;
			else
				ball.speedX = Main.BALL_SPEED;
			return true;
		}
		return false;
	}
	
	public void update() {
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
			positionX = 400; //Positon de réaparition de la balle
			positionY = 300;
			System.out.println("- 1 vie");
		}
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
