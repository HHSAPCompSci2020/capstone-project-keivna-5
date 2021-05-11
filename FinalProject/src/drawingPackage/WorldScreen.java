package drawingPackage;

import processing.core.*;


import worldSetting.Camera;
import worldSetting.CameraNoMouse;
import worldSetting.Viewfinder;
import worldSetting.World;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WorldScreen extends Screen {

	private ArrayList<Integer> keys;

	//private Camera camera;
	private CameraNoMouse cameraNoMouse;

	private World world;
	private Viewfinder viewfinder;

	public float ratioX, ratioY;


	public WorldScreen() {
		super(800,600);

		world = new World();
		//camera = new Camera();
		cameraNoMouse = new CameraNoMouse();
		viewfinder = new Viewfinder();

		keys = new ArrayList<Integer>();

	}

	//TODO: draw world + menu + tabs here
	public void draw(PApplet marker) {

		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;

		marker.scale(ratioX, ratioY);
		marker.background(255,255,255);

		//3D aspects
		marker.pushMatrix();
//		camera.draw(marker);
		cameraNoMouse.draw(marker);
		
		world.display(marker);
		marker.popMatrix();
		
		//2D viewfinder
		viewfinder.draw(marker);
		

		//control z, move forward/back
		if (checkKey(KeyEvent.VK_W)) {
			//System.out.println("moving z");
//			camera.moveZ(1);
			cameraNoMouse.moveZ(1);

			//System.out.println("W after");

		} else if (checkKey(KeyEvent.VK_S)) {
//			camera.moveZ(-1);
			cameraNoMouse.moveZ(-1);
		}
		
		//control x, move left/right
		if (checkKey(KeyEvent.VK_A)) {
//			camera.moveX(1);
			cameraNoMouse.moveX(1);

		} else if (checkKey(KeyEvent.VK_D)) {
//			camera.moveX(-1);
			cameraNoMouse.moveX(-1);
		}
		
		//control y, move up/down with "Q" and "E" keys
		if (checkKey(KeyEvent.VK_Q)) {
			cameraNoMouse.moveY(1);

		} else if (checkKey(KeyEvent.VK_E)) {
			cameraNoMouse.moveY(-1);
		}
	}

	public void checkCamera() {
	}


	public void setPlayerAtStart() {
		//camera.moveTo(start.getX(), start.getY()-15, start.getZ());
//		camera.moveTo(350, 350, 50);
		cameraNoMouse.moveTo(350, 350, 50);


	}
	
	public void keyPressed(PApplet marker) {
		//System.out.println("calling key pressed");

		if (!checkKey(marker.keyCode))
			keys.add(marker.keyCode);

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

	//calls viewfinder for tab switching
	public void mousePressed(DrawingSurface marker) {
		viewfinder.mousePressed(marker);
	}
}

