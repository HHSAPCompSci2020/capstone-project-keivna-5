
import processing.core.*;

public class DrawingSurface extends PApplet{
	
	//public float ratioX, ratioY;
//	private ArrayList<Integer> keys;
	//private Screen activeScreen;
	private WorldScreen worldScreen;
//	private ArrayList<Screen> screens;
	//private Camera camera;

	
	public DrawingSurface() {
//		screens = new ArrayList<Screen>();
//		keys = new ArrayList<Integer>();
		
//		WorldScreen worldScreen = new WorldScreen();
		worldScreen = new WorldScreen();

//		screens.add(worldScreen);
		
//		PortfolioScreen portfolioScreen = new PortfolioScreen(this);
//		screens.add(portfolioScreen);
		
//		activeScreen = screens.get(0);
		
		//camera = new Camera();

	}
	
	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
//		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
//		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
		size(worldScreen.DRAWING_WIDTH, worldScreen.DRAWING_HEIGHT, processing.core.PConstants.P3D);
	}
	
	public void setup() {
		surface.setResizable(true);
//		worldScreen.setup();
//		for (Screen s : screens)
//			s.setup();
//		worldScreen.setPlayerAtStart();
	}
	
	public void draw() {
//		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
//		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
//		scale(ratioX, ratioY);
		
//		activeScreen.draw();
		worldScreen.draw(this);
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
		worldScreen.keyPressed(this);
//		if (!checkKey(keyCode))
//			keys.add(keyCode);
//
////		if (checkKey(KeyEvent.VK_SPACE))
////			camera.jump();
	}

	// Removes key from array list
	public void keyReleased() {
		worldScreen.keyReleased(this);
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
//	public void mousePressed() {
//		activeScreen.mousePressed();
//	}
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
//	public void switchScreen(int i) {
//		activeScreen = screens.get(i);
//	}
}
