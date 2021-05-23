package drawingPackage;

import processing.core.*;

import worldSetting.CameraNoMouse;
import worldSetting.SoundPlayer;
import worldSetting.Viewfinder;
import worldSetting.World;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Creates all the elements that pertain with using the world
 * @author Katia and Elise
 *
 */
public class WorldScreen extends Screen {

	private ArrayList<Integer> keys;
	private CameraNoMouse cameraNoMouse;
	private World world;
	private Viewfinder viewfinder;
	
	private boolean playingCarSound = false;
	
	/**
	 * Ratio of the screen
	 */
	public float ratioX, ratioY;
	
	/**
	 * The constant for the speed at which movement should occur
	 */
	public static final int keyMoveFactor = 8;
	
	/**
	 * The constants for the size of the screen
	 */
	public static final int screenWidth = 800, screenHeight = 600;

	/**
	 * Sets up the world, camera, viewfinder, and keys arraylist
	 * sets the size of the screen too
	 * @param marker the PApplet that is shared
	 * @pre marker can't be null
	 */
	public WorldScreen(PApplet marker) {
		super(screenWidth, screenHeight);

		world = new World(marker);
		cameraNoMouse = new CameraNoMouse(1, .75f, PConstants.PI / 3f, 60f);
		viewfinder = new Viewfinder(marker);
		keys = new ArrayList<Integer>();
	}

	/**
	 * Draws the world and viewfinder and catches key presses
	 * @param marker PApplet that is shared
	 * @pre marker can't be null
	 * @post changes the PApplet
	 */
	public void draw(PApplet marker) {
		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;

		marker.scale(ratioX, ratioY);
		marker.background(255,255,255);

		//3D aspects
		marker.pushMatrix();
		cameraNoMouse.draw(marker);
		world.draw(marker, viewfinder.getISOvalue(), viewfinder.getLightSourceY(), 
				viewfinder.getLightSourceX(), viewfinder.getBackgroundEnum());
		marker.popMatrix(); //get out of 3D world
		
		viewfinder.draw(marker);
		
		if(cameraNoMouse.getX() <= 350 + 100) {
			SoundPlayer.playCarSound();
		}
				
		//control z, move forward/back
		if (checkKey(KeyEvent.VK_W)) {
			cameraNoMouse.moveY(keyMoveFactor*-1);
		} else if (checkKey(KeyEvent.VK_S)) {
			cameraNoMouse.moveY(keyMoveFactor);
		}
		
		//control x, move left/right
		if (checkKey(KeyEvent.VK_A)) {
			cameraNoMouse.moveX(keyMoveFactor*-1);
		} else if (checkKey(KeyEvent.VK_D)) {
			cameraNoMouse.moveX(keyMoveFactor);
		}
		
		//control y, move up/down with "Q" and "E" keys
		if (checkKey(KeyEvent.VK_UP)) {
			cameraNoMouse.moveZ(keyMoveFactor);
		} else if (checkKey(KeyEvent.VK_DOWN)) {
			cameraNoMouse.moveZ(keyMoveFactor*-1);
		}
		
		//control y, move up/down with "Q" and "E" keys
		if (checkKey(KeyEvent.VK_LEFT)) {
			cameraNoMouse.setPan(cameraNoMouse.getPan() + marker.QUARTER_PI / 180 * keyMoveFactor);
		} else if (checkKey(KeyEvent.VK_RIGHT)) {
			cameraNoMouse.setPan(cameraNoMouse.getPan() - marker.QUARTER_PI / 180 * keyMoveFactor);
		}
		
		
	}

	/**
	 * Sets the position of the camera to be in a certain spot and spun a certain amount
	 * @param marker
	 * @pre marker can't be null
	 * @post changes the camera's position and pan
	 */
	public void setCameraAtStart(PApplet marker) {
		cameraNoMouse.moveTo(-350, -700, 700); //350, -200, 700 for viewing the water better
		cameraNoMouse.setPan(marker.QUARTER_PI * 7); //marker.QUARTER_PI * 6
	}
	
	/**
	 * Adds the key that is being pressed to the key array list
	 * @param marker the PApplet that is shared
	 * @pre marker can't be null
	 */
	public void keyPressed(PApplet marker) {
		if (!checkKey(marker.keyCode))
			keys.add(marker.keyCode);
	}

	/**
	 * Removes key from array list
	 * @param marker the PApplet that is shared
	 * @pre marker can't be null
	 */
	public void keyReleased(PApplet marker) {
		while (checkKey(marker.keyCode))
			keys.remove(new Integer(marker.keyCode));
	}

	/** 
	 * Checks if given key code is in the array list
	 * @param i index of the key
	 * @return whether it is in the array list
	 */
	public boolean checkKey(int i) {
		return keys.contains(i);
	}

	/**
	 * Checks to see if a key has been pressed
	 * @param code The code for the key clicked
	 * @return whether the keyboard button has been clicked
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	/**
	 * Calls ViewFinder for tab switching
	 * @param marker the DrawingSurface
	 * @pre marker can't be null
	 */
	public void mousePressed(DrawingSurface marker) {
		viewfinder.mousePressed(marker);
	}
}

