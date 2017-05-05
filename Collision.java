package david_nour.arcanoid;

import java.awt.Rectangle;

public class Collision {
	
	public String checkSideCollision(Rectangle r1, Rectangle r2) {
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
	    return(collision);
	    
	   
	
		
		
		
	
}
