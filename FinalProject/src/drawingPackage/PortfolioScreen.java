package drawingPackage;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import portfolio.Portfolio;
import processing.core.PApplet;

/**
 * The Screen that draws the Portfolio and implements all other user interactions
 * @author elise
 */
public class PortfolioScreen extends Screen {
	
	public float ratioX, ratioY;
	private Rectangle toggle;
	private Portfolio portfolio;
	public static final int toggleX = 25, toggleY = 25, toggleWidth = 120, toggleHeight = 20, toggleRadius = 10, screenWidth = 800, screenHeight = 600;



	/**
	 * PortfolioScreen constructor
	 * Initializes all fields
	 */
	public PortfolioScreen() {
		super(screenWidth, screenHeight);
		toggle = new Rectangle(toggleX, toggleY, toggleWidth, toggleHeight);
		portfolio = new Portfolio();
	}
	
	/**
	 * Draws a portfolio of photos as well as interaction keys
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {
		
		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;
				
		marker.pushStyle();
		
		marker.background(0,0,0);
		marker.fill(120);

		portfolio.draw(marker);
		
		//toggle
		marker.rect(toggle.x - 5, toggle.y - 5, toggle.width, toggle.height, toggleRadius);
		
		//toggle text
		marker.fill(0);
		marker.textSize(12);
		String str = "Switch to Viewfinder";
		float w = marker.textWidth(str);
		marker.text(str, toggle.x+toggle.width/2-w/2 - 5, toggle.y+toggle.height/2);
		
		marker.popStyle();
		
		//control y, move up/down with "Q" and "E" keys
//				if (checkKey(KeyEvent.VK_UP)) {
//					cameraNoMouse.moveZ(Constants.keyMoveFactor);
//
//				} else if (checkKey(KeyEvent.VK_DOWN)) {
//				
//					cameraNoMouse.moveZ(Constants.keyMoveFactor*-1);
//				}
				
	}
	
	/**
	 * Checks if the toggle has been pressed and switches screen through the DrawingSurface
	 * @pre DrawingSurface marker cannot be null
	 */
	public void mousePressed(DrawingSurface marker) {
		Point p = new Point(marker.mouseX,marker.mouseY);

		if (toggle.contains(p)) {
			marker.switchScreen(0);
		}
	}
	
//	public void mouseWheel(PApplet marker, MouseEvent event) {
//		float e = event.getCount();
//		marker.mouseEvent();
//		  float e = event.getCount();
//		  System.out.println(e);
//		}

	
}