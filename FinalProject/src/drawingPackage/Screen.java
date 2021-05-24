package drawingPackage;


import photography.Viewfinder;
import processing.core.PApplet;

/**
 * Creates the skeleton of the screens
 * @author Katia and Elise
 *
 */
public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * Creates a screen
	 * @param width Width of the screen
	 * @param height Height of the screen
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Implement with the screen's setup
	 */
	public void setup() {}
	
	/**
	 * Implement with whatever the screen needs to draw
	 * @param drawer PApplet that is shared
	 * @pre drawer must not be null
	 * @post changes the PApplet drawer
	 */
	public void draw(PApplet drawer) {}
	
	/**
	 * Implement with whatever the screen needs to draw
	 * @param drawer PApplet that is shared
	 * @param viewfinder for accessing photos to display
	 * @pre drawer must not be null
	 * @post changes the PApplet drawer
	 */
	public void draw(PApplet drawer, Viewfinder viewfinder) {}
	
	/**
	 * Sees what to do if the mouse is pressed
	 * @param surface The shared DrawingSurface
	 * @pre surface can't be null
	 */
	public void mousePressed(DrawingSurface surface) {}

	/**
	 * Sees what to do if the key is pressed
	 * @param marker The shared PApplet
	 * @pre marker can't be null
	 */
	public void keyPressed(PApplet marker) {}
	
	/**
	 * Sees what to do if the key is released
	 * @param marker The shared PApplet
	 * @pre marker can't be null
	 */
	public void keyReleased(PApplet marker) {}
}
