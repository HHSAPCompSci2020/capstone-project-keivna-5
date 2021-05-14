package drawingPackage;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import processing.core.*;

public class DrawingSurface extends PApplet{

	public float ratioX, ratioY;
	private Screen activeScreen;
	private WorldScreen worldScreen;
	private PortfolioScreen portfolioScreen;
	private ArrayList<Screen> screens;

	public DrawingSurface() {
		screens = new ArrayList<Screen>();

		//0
		worldScreen = new WorldScreen(this);
		screens.add(worldScreen);

		//1
		portfolioScreen = new PortfolioScreen();
		screens.add(portfolioScreen);

		//set activeScreen to world screen. first thing that shows is world screen
		activeScreen = screens.get(0);
	}

	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
	}

	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();

		//		worldScreen.setPlayerAtStart();
	}

	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		scale(ratioX, ratioY);

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
	
	public void mouseWheel(MouseEvent event) {
		activeScreen.mouseWheel(event);
	}
}
