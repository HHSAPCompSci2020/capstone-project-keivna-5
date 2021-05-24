package drawingPackage;

import java.awt.Point;

import java.awt.Rectangle;

import portfolio.Portfolio;
import processing.core.PApplet;

/**
 * The Screen that draws the Portfolio and implements all other user interactions
 * @author Elise
 */
public class PortfolioScreen extends Screen {
	
	private Portfolio portfolio;	
	private static final int screenWidth = 800, screenHeight = 600;
	
	/**
	 * PortfolioScreen constructor
	 * Initializes all fields
	 */
	public PortfolioScreen() {
		super(screenWidth, screenHeight);
		portfolio = new Portfolio(screenWidth, screenHeight);
	}
	
	/**
	 * Draws a portfolio of photos as well as interaction keys
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {	
		marker.pushStyle();
		portfolio.draw(marker);
		marker.popStyle();			
	}
	
	/**
	 * Checks if the toggle has been pressed and switches screen through the DrawingSurface
	 * @pre DrawingSurface marker cannot be null
	 */
	public void mousePressed(DrawingSurface marker) {
		portfolio.mousePressed(marker);
	}
}