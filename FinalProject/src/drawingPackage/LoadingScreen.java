package drawingPackage;

import processing.core.PApplet;

/**
 * The Screen that draws the Glossary
 * @author Elise
 */
public class LoadingScreen extends Screen {

	private static final double screenWidth = 800, screenHeight = 600;
	
	/**
	 * LoadingScreen constructor
	 * Initializes all fields
	 */
	public LoadingScreen() {
		super((int)screenWidth, (int)screenHeight);
	}
	
	/**
	 * Draws a loading screen
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {				
		marker.pushStyle();
		
		marker.background(255); //light blue
		marker.fill(0);
		marker.textSize(12);
		
		//ISO text
		marker.text("Loading...", 200, 200);

		marker.popStyle();			
	}
}