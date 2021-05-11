package drawingPackage;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.*;

public class DrawingSurface extends PApplet{

	//public float ratioX, ratioY;
	//	private ArrayList<Integer> keys;
	private Screen activeScreen;
	private WorldScreen worldScreen;
	private PortfolioScreen portfolioScreen;
	private ArrayList<Screen> screens;

	//private Rectangle button;

	//public float ratioX, ratioY;


	public DrawingSurface() {
		screens = new ArrayList<Screen>();

		//0
		worldScreen = new WorldScreen();
		screens.add(worldScreen);

		//1
		portfolioScreen = new PortfolioScreen();
		screens.add(portfolioScreen);

		//set activeScreen to world screen. first thing that shows is world screen
		activeScreen = screens.get(0);

		//button = new Rectangle(800/2-100,600/2-50,200,100);

	}

	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		//		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
		//size(worldScreen.DRAWING_WIDTH, worldScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
	}

	public void setup() {
		surface.setResizable(true);
		//worldScreen.setup();
		for (Screen s : screens)
			s.setup();

		//		worldScreen.setPlayerAtStart();
	}

	public void draw() {
		//		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		//		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		//scale(ratioX, ratioY);

		activeScreen.draw(this);

		popMatrix();

	}

	public void keyPressed() {
		activeScreen.keyPressed(this);
	}

	// Removes key from array list
	public void keyReleased() {
		activeScreen.keyReleased(this);


		//		while (checkKey(keyCode))
		//			keys.remove(new Integer(keyCode));
	}

	//
	//	// Checks if given key code is in the array list
	//	public boolean checkKey(int i) {
	//		return keys.contains(i);
	//	}

	//	public void keyPressed() {
	//		keys.add(keyCode);
	//	}

	//	public void keyReleased() {
	//		while(keys.contains(keyCode))
	//			keys.remove(new Integer(keyCode));
	//	}

	//	public boolean isPressed(Integer code) {
	//		return keys.contains(code);
	//	}
	//	
	public void mousePressed() {
		activeScreen.mousePressed(this);
	}

	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
}
