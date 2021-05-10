
import java.awt.Point;
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

	private Rectangle button;

	//public float ratioX, ratioY;


	public DrawingSurface() {
		screens = new ArrayList<Screen>();

		//0
		worldScreen = new WorldScreen();
		screens.add(worldScreen);

		//1
		portfolioScreen = new PortfolioScreen();
		screens.add(portfolioScreen);

		activeScreen = screens.get(1);

		button = new Rectangle(800/2-100,600/2-50,200,100);

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

		//hint(ENABLE_DEPTH_TEST);
		activeScreen.draw(this);


		//switch tabs
//		hint(DISABLE_DEPTH_TEST);
//		rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
//		fill(0);
//		String str = "Click me!";
//		float w = textWidth(str);
//		text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		

		//		worldScreen.draw(this);
		//activeScreen.checkCamera(this);

		//		if (checkKey(KeyEvent.VK_W)) {
		//			System.out.println("W before");
		//			camera.moveZ(1);
		//			System.out.println("W after");
		//
		//		} else if (checkKey(KeyEvent.VK_S)) {
		//			camera.moveZ(-1);
		//		}
		//		if (checkKey(KeyEvent.VK_A))
		//			camera.moveX(1);
		//		else if (checkKey(KeyEvent.VK_D))
		//			camera.moveX(-1);



		popMatrix();

	}

	public void keyPressed() {
		//worldScreen.keyPressed(this);
		activeScreen.keyPressed(this);

		//		if (!checkKey(keyCode))
		//			keys.add(keyCode);
		//
		////		if (checkKey(KeyEvent.VK_SPACE))
		////			camera.jump();
	}

	// Removes key from array list
	public void keyReleased() {
		//worldScreen.keyReleased(this);
		activeScreen.keyReleased(this);


		//		while (checkKey(keyCode))
		//			keys.remove(new Integer(keyCode));
	}

//	//TODO: turn into tabs here
//	public void mousePressed() {
//		Point p = actualCoordinatesToAssumed(new Point(mouseX,mouseY));
//		if (button.contains(p)) {
//			switchScreen(ScreenSwitcher.SCREEN2);
//			System.out.println("switch screen");
//		}
//	}

//	public Point assumedCoordinatesToActual(Point assumed) {
//		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
//	}
//
//	public Point actualCoordinatesToAssumed(Point actual) {
//		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
//	}

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
			System.out.println("calling mouse pressed from dsurface");
			System.out.println("active screen: " + activeScreen);
			activeScreen.mousePressed(this);
		}
	//	
	//	public void mouseMoved() {
	//		activeScreen.mouseMoved();
	//	}
	//	
	//	public void mouseDragged() {
	//		activeScreen.mouseDragged();
	//	}
	//	
	//	public void mouseReleased() {
	//		activeScreen.mouseReleased();
	//	}
	//	
	//	public Point assumedCoordinatesToActual(Point assumed) {
	//		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	//	}
	//
	//	public Point actualCoordinatesToAssumed(Point actual) {
	//		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	//	}
	//
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
}
