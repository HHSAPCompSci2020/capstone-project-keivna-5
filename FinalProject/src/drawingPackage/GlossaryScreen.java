package drawingPackage;

import java.awt.Point;

import java.awt.Rectangle;

import portfolio.Portfolio;
import processing.core.PApplet;

/**
 * The Screen that draws the Portfolio and implements all other user interactions
 * @author Elise
 */
public class GlossaryScreen extends Screen {
	
	//private Rectangle toggle;
	//private Portfolio portfolio;
	
	/**
	 * Represents the ratios for the size of the screen
	 */
	public float ratioX, ratioY;
	
	/**
	 * Constants for the size of the toggle button
	 */
	public static final int toggleX = 25, toggleY = 25, toggleWidth = 120, toggleHeight = 20, toggleRadius = 10;
	
	/**
	 * Constants for the size of the screen
	 */
	public static final int screenWidth = 800, screenHeight = 600;
	
	/**
	 * PortfolioScreen constructor
	 * Initializes all fields
	 */
	public GlossaryScreen() {
		super(screenWidth, screenHeight);
	}
	
	/**
	 * Draws a portfolio of photos as well as interaction keys
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {
		
//		ratioX = (float)marker.width/this.DRAWING_WIDTH;
//		ratioY = (float)marker.height/this.DRAWING_HEIGHT;
				
		marker.pushStyle();
		
		marker.background(0,0,0);
		marker.fill(120);

		marker.text("ISO: ", 100, 100);
		
		marker.popStyle();			
	}
}