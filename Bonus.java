package david_nour.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
//TODO collisions
public class Bonus {
	public static final int SLOW_TIME = 1;
	public static final int POWERFULL_BALL = 2;
	
	private int bonusType;
	private double positionX, positionY;
	private double speedY;
	private boolean active;
	private Shape collider;
	private Color color;
	
	public Bonus(double positionX, double positionY, int bonusType) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.bonusType = bonusType;
		this.speedY = 3;
		this.active = true;
		this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, 25, 15);	
		switch (bonusType) {
		case 1:
			this.color = Color.BLUE;
			break;
		case 2:
			this.color = Color.ORANGE;
			break;
		default:
			break;
		}
	}	
	
	public int getBonusType() {
		return this.bonusType;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean bool) {
		this.active = bool;
	}
	
	public boolean racketCollision(Racket racket) {
		return this.collider.intersects(racket.getCollider().getBounds2D());
	}
	
	public void paintBonus(Graphics gRaw) {
		if(active) {
			Graphics2D g = (Graphics2D) gRaw;
			this.collider = new Rectangle2D.Double((int) this.positionX, (int) this.positionY, 25, 15);	
			g.setColor(this.color);
			g.draw(collider);
			g.fill(collider);
		}
	}
	
	public void update() {
		if(active) {
			positionY += speedY;
			if(positionY + speedY >= Main.HEIGHT) {
				this.active = false;
			}
		}	
	}
}
