package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Brick {
	private double positionX, positionY;
	private double width = Main.BRICK_WIDTH;
	private double height = Main.BRICK_HEIGHT;
	private boolean active = true;
	private int tapToDeath = 1;
	private Shape collider;

	
	public Brick(double x, double y) {
		positionX = x;
		positionY = y;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);	
	}
	
	public Brick(double x, double y, int tapToDeath) {
		positionX = x;
		positionY = y;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);
		this.tapToDeath = tapToDeath;
	}
	
	public int getTapToDeath() {
		return this.tapToDeath;
	}

	public void setTapToDeath(int tapToDeath) {
		this.tapToDeath = tapToDeath ;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void setActive(boolean b) {
		this.active = b;
	}
	
	public double getPositionX() {
		return this.positionX;
	}
	
	public double getPositionY() {
		return this.positionY;
	}
	
	public Shape getCollider() {
		return this.collider;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void destroy() {
		
	}
	
	public void paintBrickCustom(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);
	}
	
	public void paintBrick(Graphics gRaw) {
		Graphics2D g = (Graphics2D) gRaw;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, this.width, this.height);
		
		if (this.tapToDeath <= 0 && active && positionY < 150) {
			Font font = new Font("courrier", Font.BOLD, 28);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString("+"+(10*Main.SCORE_BONUS), (int)(this.positionX + this.width/2) - 20, (int)(this.positionY + this.height/2));
			update();			
		} else if (this.tapToDeath > 0) {
			switch (this.tapToDeath) {
			case 1:
				g.setColor(Color.GREEN);
				break;
			case 2:
				g.setColor(Color.ORANGE);
				break;
			case 3:
				g.setColor(Color.RED);
				break;
			default:
				break;
			}
		
		
			//System.out.println("Dessin Brick");
			g.draw(collider);
			g.fill(collider);		
		}
		
			
	}
	
	public synchronized void update() {
		
			positionY += 0.9f;
			
		
	}
	
	public void run() {
		while(!Model.gameOver && Model.gameOver) {			
				while(tapToDeath <= 0 && active) {					
					update();
					//if (positionY <= 0) {active = false;}
				} 
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}						
		}
	}
}
