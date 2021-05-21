package drawingPackage;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import processing.core.*;

/**
 * Creates the screens for the main parts of the application
 * @author Katia and Elise
 *
 */
public class DrawingSurface extends PApplet{

	public float ratioX, ratioY;
	private Screen activeScreen;
	private WorldScreen worldScreen;
	private PortfolioScreen portfolioScreen;
	private ArrayList<Screen> screens;

	/**
	 * Creates the different screens for different parts of the program
	 * including worldScreen, portfolioScreen and whatever the beginning screen
	 * when opening should be, in this case being the worldScreen
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();

		worldScreen = new WorldScreen(this); //0
		screens.add(worldScreen);

		portfolioScreen = new PortfolioScreen(); //1
		screens.add(portfolioScreen);

		activeScreen = screens.get(0); //set activeScreen to world screen
	}

	/**
	 * Sets the size of the screen for the 3D world
	 */
	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
		
	}

	/**
	 * Sets up the two screens, and if it is in the worldScreen
	 * then it sets the position of the camera by calling setCameraAtStart()
	 */
	public void setup() {
//		frameRate(480); //speed up because all the 3D elements make everything a lot slower
		worldScreen.setup(this); //for background image
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
		
		if(activeScreen instanceof WorldScreen)
			((WorldScreen) activeScreen).setCameraAtStart(this);
	}

	/**
	 * Draws the active screen and sets up the ratio of the screen to draw
	 */
	public void draw() {
		ratioX = (float) width/activeScreen.DRAWING_WIDTH;
		ratioY = (float) height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		scale(ratioX, ratioY);
		activeScreen.draw(this);
		popMatrix();
	}

	/**
	 * Calls the activeScreen's keyPressed()
	 */
	public void keyPressed() {
		activeScreen.keyPressed(this);
	}

	/**
	 * Calls the activeScreen's keyReleased()
	 */
	public void keyReleased() {
		activeScreen.keyReleased(this);
	}
	
	/**
	 * Calls the activeScreen's mousePressed()
	 */
	public void mousePressed() {
		activeScreen.mousePressed(this);
	}

	/**
	 * Switches the active screen to what is passed in
	 * @param i The index of the screen
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	/**
	 * Calls the activeScreen's mouseWheel()
	 * @param event The mouse action that was done
	 */
	public void mouseWheel(MouseEvent event) {
		activeScreen.mouseWheel(event);
	}
}
