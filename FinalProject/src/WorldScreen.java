
import processing.core.*;


import processing.opengl.*;
import worldSetting.Camera;
import worldSetting.Element;
import worldSetting.World;
import java.awt.event.KeyEvent;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import mazeexample.Maze;
import mazeexample.Player;


public class WorldScreen extends Screen {

	//private ArrayList<Integer> keys = new ArrayList<Integer>();
	private ArrayList<Integer> keys;
	public float ratioX, ratioY;

	private Camera camera;

	//private DrawingSurface surface;
//	private Rectangle button;
	private World world;


	
	public WorldScreen() {

		super(800,600);
		//this.surface = surface;

//		button = new Rectangle(800/2-100,600/2-50,200,100);

		//		world = new World(surface);
		world = new World();
		camera = new Camera();

		keys = new ArrayList<Integer>();


	}
	
//	public void settings() {
//		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
////		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
//		size(DRAWING_WIDTH, DRAWING_HEIGHT, processing.core.PConstants.P3D);
//
//	}
	
//	public void setup() {
////		surface.setResizable(true);
//		setResizable(true);
//		setup();
//		
//		for (Screen s : screens)
//			s.setup();
//	}
	
	

	//TODO: draw world + menu + tabs here
	public void draw(PApplet marker) {
		
//		ratioX = (float)width/this.DRAWING_WIDTH;
//		ratioY = (float)height/this.DRAWING_HEIGHT;
//		
		//scale(ratioX, ratioY);

//		background(255,255,255);

		//		surface.box(20);

		world.display(marker);
		camera.draw(marker);
		//checkCamera();
		
		//world.update(camera);
		//		player.draw(this);

		//		if (surface.checkKey(KeyEvent.VK_W))
		//			camera.moveZ(1);
		//		else if (surface.checkKey(KeyEvent.VK_S))
		//			camera.moveZ(-1);
		//		if (surface.checkKey(KeyEvent.VK_A))
		//			camera.moveX(1);
		//		else if (surface.checkKey(KeyEvent.VK_D))
		//			camera.moveX(-1);
		//		if (surface.checkKey(KeyEvent.VK_SPACE))
		//			camera.jump();


		//		
		//		//switch tabs
		//		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		//		surface.fill(0);
		//		String str = "Click me!";
		//		float w = surface.textWidth(str);
		//		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		//		
//		popStyle();
	}

	public void checkCamera() {
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
	}

//	public void update(Camera c) {
//		//c.act(new Element());
//	}

	//TODO: turn into tabs here
//	public void mousePressed() {
//		Point p = actualCoordinatesToAssumed(new Point(mouseX,mouseY));
//		if (button.contains(p))
//			switchScreen(ScreenSwitcher.SCREEN2);
//	}

	public void setPlayerAtStart() {
		//		camera.moveTo(start.getX(), start.getY()-15, start.getZ());
		camera.moveTo(350, 350, 50);

	}

	// Checks if given key code is in the array list
	//	private boolean checkKey(int i) {
	//		return keys.contains(i);
	//	}

	public void keyPressed(PApplet marker) {
		System.out.println("calling key pressed");
		
		if (!checkKey(marker.keyCode))
			keys.add(marker.keyCode);
		
		if (checkKey(KeyEvent.VK_W)) {
			System.out.println("W before");
			camera.moveZ(1);
			System.out.println("W after");

		} else if (checkKey(KeyEvent.VK_S)) {
			camera.moveZ(-1);
		}
		if (checkKey(KeyEvent.VK_A))
			camera.moveX(1);
		else if (checkKey(KeyEvent.VK_D))
			camera.moveX(-1);


		//		if (checkKey(KeyEvent.VK_SPACE))
		//			camera.jump();
	}

	// Removes key from array list
	public void keyReleased(PApplet marker) {
		while (checkKey(marker.keyCode))
			keys.remove(new Integer(marker.keyCode));
	}

	// Checks if given key code is in the array list
	public boolean checkKey(int i) {
		return keys.contains(i);
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

//	public void mousePressed() {
//		activeScreen.mousePressed();
//	}
//
//	public void mouseMoved() {
//		activeScreen.mouseMoved();
//	}

//	public void mouseDragged() {
//		activeScreen.mouseDragged();
//	}
//
//	public void mouseReleased() {
//		activeScreen.mouseReleased();
//	}

	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

}

