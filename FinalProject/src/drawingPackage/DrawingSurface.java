package drawingPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import processing.core.*;

/**
 * Creates the screens for the main parts of the application
 * @author Elise and Katia
 *
 */
public class DrawingSurface extends PApplet{

	private float ratioX, ratioY;
	private Screen activeScreen;
	private WorldScreen worldScreen;
	private PortfolioScreen portfolioScreen;
	private GlossaryScreen glossaryScreen;
	private Rectangle viewfinderButton, portfolioButton, glossaryButton;
	private final int buttonWidth = 100, buttonHeight = 30;

	/**
	 * Creates the different screens for different parts of the program
	 * including worldScreen, portfolioScreen and whatever the beginning screen
	 * when opening should be, in this case being the worldScreen
	 */
	public DrawingSurface() {
		worldScreen = new WorldScreen(this);
		portfolioScreen = new PortfolioScreen();
		glossaryScreen = new GlossaryScreen();
		
		activeScreen = worldScreen; //set activeScreen to world screen
		
		//initialize buttons
		viewfinderButton = new Rectangle (0, 0, buttonWidth, buttonHeight);
		portfolioButton = new Rectangle (buttonWidth, 0, buttonWidth, buttonHeight);
		glossaryButton = new Rectangle (buttonWidth*2, 0, buttonWidth, buttonHeight);
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
		surface.setResizable(true);
		worldScreen.setup();
		portfolioScreen.setup();
		glossaryScreen.setup();

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

		//tabs
		textSize(14);

		//viewfinder button
		fill(200);
		if (activeScreen.equals(worldScreen)) { //darker button to show selection
			fill(150);
		}
		rect(viewfinderButton.x, viewfinderButton.y, viewfinderButton.width, viewfinderButton.height);

		//viewfinder text
		fill(0);
		text("Viewfinder", viewfinderButton.x + 10, viewfinderButton.y + (3*viewfinderButton.height/4));

		//portfolio button
		fill(200);
		if (activeScreen.equals(portfolioScreen)) { //darker button to show selection
			fill(150);
		}
		rect(portfolioButton.x, portfolioButton.y, portfolioButton.width, portfolioButton.height);

		//portfolio text
		fill(0);
		text("Portfolio", portfolioButton.x + 10, portfolioButton.y + (3*viewfinderButton.height/4));

		//glossary button
		fill(200);
		if (activeScreen.equals(glossaryScreen)) { //darker button to show selection
			fill(150);
		}
		rect(glossaryButton.x, glossaryButton.y, glossaryButton.width, glossaryButton.height);

		//glossary text
		fill(0);
		text("Glossary", glossaryButton.x + 10, glossaryButton.y + (3*viewfinderButton.height/4));

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
		Point p = new Point(mouseX, mouseY);

		if (viewfinderButton.contains(p)) {
			activeScreen = worldScreen;
		} else if (portfolioButton.contains(p)) {
			activeScreen = portfolioScreen;
		} else if (glossaryButton.contains(p)) {
			activeScreen = glossaryScreen;
		}
		activeScreen.mousePressed(this);
	}
}
