package drawingPackage;

import processing.core.*;


import worldSetting.CameraNoMouse;
import worldSetting.Viewfinder;
import worldSetting.World;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class WorldScreen extends Screen {

	private ArrayList<Integer> keys;
	private CameraNoMouse cameraNoMouse;
	private World world;
	private Viewfinder viewfinder;
	public float ratioX, ratioY;
	public static final int keyMoveFactor = 2;

	public WorldScreen(PApplet marker) {
		super(Constants.screenWidth,Constants.screenHeight);

		world = new World(marker);
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
		cameraNoMouse.draw(marker);
		
		world.display(marker);
		marker.popMatrix(); //get out of 3D world
		
		viewfinder.draw(marker);
				
		//control z, move forward/back
		if (checkKey(KeyEvent.VK_W)) {
			cameraNoMouse.moveY(keyMoveFactor*-1);

		} else if (checkKey(KeyEvent.VK_S)) {
			cameraNoMouse.moveY(keyMoveFactor);
		}
		//control x, move left/right
		if (checkKey(KeyEvent.VK_A)) {
			cameraNoMouse.moveX(keyMoveFactor);


		} else if (checkKey(KeyEvent.VK_D)) {
			cameraNoMouse.moveX(keyMoveFactor*-1);
		}
		//control y, move up/down with "Q" and "E" keys
		if (checkKey(KeyEvent.VK_UP)) {
			cameraNoMouse.moveZ(keyMoveFactor);

		} else if (checkKey(KeyEvent.VK_DOWN)) {
		
			cameraNoMouse.moveZ(keyMoveFactor*-1);
		}
	}

	public void setPlayerAtStart() {
		//camera.moveTo(start.getX(), start.getY()-15, start.getZ());
		cameraNoMouse.moveTo(350, 350, 50);
	}
	
	public void keyPressed(PApplet marker) {
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

