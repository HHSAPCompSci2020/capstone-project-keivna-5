package drawingPackage;
import java.awt.Point;
import java.awt.event.MouseEvent;

import processing.core.PApplet;

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
	 * Sees what to do if the mouse is pressed
	 * @param surface The shared DrawingSurface
	 * @pre surface can't be null
	 */
	public void mousePressed(DrawingSurface surface) {}
	
	/**
	 * Sees what to do if the mouse is moved
	 */
	public void mouseMoved() {}
	
	/**
	 * Sees what to do if the mouse is dragged
	 */
	public void mouseDragged() {}
	
	/**
	 * Sees what to do if the mouse is released
	 */
	public void mouseReleased() {}
	
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

	/**
	 * Sees what to do if the mouse wheel does something
	 * @param event The movement that happened to the mouseWheel
	 * @pre event can't be null
	 */
	public void mouseWheel(MouseEvent event) {}
}
