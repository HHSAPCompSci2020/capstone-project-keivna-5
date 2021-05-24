package drawingPackage;


import photography.Viewfinder;
import portfolio.Portfolio;
import processing.core.PApplet;

/**
 * The Screen that draws the Portfolio
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
	 * Draws a portfolio of photos
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker, Viewfinder viewfinder) {	
		marker.pushStyle();
		portfolio.draw(marker, viewfinder);
		marker.popStyle();			
	}
	
	/**
	 * calls portfolio's mouse pressed for image viewing
	 * @pre DrawingSurface marker cannot be null
	 */
	public void mousePressed(DrawingSurface marker) {
		portfolio.mousePressed(marker);
	}
}