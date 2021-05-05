import java.awt.Point;
import java.util.ArrayList;
import processing.core.PApplet;
//import screens.*;

public class DrawingSurface extends PApplet{
	
	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	
	public DrawingSurface() {
		
		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		
		FirstScreen screen1 = new FirstScreen(this);
		screens.add(screen1);
		
		SecondScreen screen2 = new SecondScreen(this);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
		
	}
	
	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
//	public void settings() {
//		//fullScreen(P3D);
//		super.size(800, 500, P3D);
//		 
//	}

//	public void setup() {
//		
//		strokeWeight(2);
//		this.frameRate(1000);
////		player = new Player();
////		player.setup(this);
////		maze = new Maze(20);
////		maze.setPlayerAtStart(player);
//		
//	}

//	public void draw() {
//		
//		noCursor();
//		background(51);
////		maze.display(this);
////		maze.update(player);
////		player.draw(this);
////
////		if (checkKey(KeyEvent.VK_W))
////			player.moveZ(1);
////		else if (checkKey(KeyEvent.VK_S))
////			player.moveZ(-1);
////		if (checkKey(KeyEvent.VK_A))
////			player.moveX(1);
////		else if (checkKey(KeyEvent.VK_D))
////			player.moveX(-1);
//		
//
//	}

//	public void keyPressed() {
////		if (!checkKey(keyCode))
////			keys.add(keyCode);
////
////		if (checkKey(KeyEvent.VK_SPACE))
////			player.jump();
//	}

	// Removes key from array list
//	public void keyReleased() {
//		while (checkKey(keyCode))
//			keys.remove(new Integer(keyCode));
//	}
//
//	// Checks if given key code is in the array list
//	private boolean checkKey(int i) {
//		return keys.contains(i);
//	}
}
