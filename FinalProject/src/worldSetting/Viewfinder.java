package worldSetting;

import java.awt.Point;
import java.awt.Rectangle;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the 2D aspect of the World, where photos are taken and settings are changed
 * @author elise
 */
public class Viewfinder {
	private Rectangle toggle, shutterButton;
	private Shutter shutter;
	/**
	 * Represents the ratios for the size of the screen
	 */
	public float ratioX, ratioY;
	
	/**
	 * Constant for the spacing for the ViewFinder
	 */
	public static final float viewfinderIndent = 60;
	
	/**
	 * Constants for the size of the toggle button
	 */
	public static final int toggleX = 25, toggleY = 25, toggleWidth = 120, toggleHeight = 20, toggleRadius = 10;
	
	/**
	 * Constants for the size of the screen
	 */
	public static final int screenWidth = 800, screenHeight = 600;

	/**
	 * Initializes fields
	 */
	public Viewfinder() {
		toggle = new Rectangle(toggleX,toggleY, toggleWidth, toggleHeight);
		shutterButton = new Rectangle(620, 30, 120, 30);
		shutter = new Shutter();		
	}
	
	/**
	 * Draws the 2D border ViewFinder surrounding the world
	 * @param PApplet marker
	 * @pre marker can't be null
	 * @post the PApplet marker will change
	 */
	public void draw(PApplet marker) {		
		marker.pushMatrix();		
		marker.fill(0);
		
		//viewfinder black border
		marker.strokeWeight(0);
		marker.rect(0, 0, marker.width, viewfinderIndent); //top
		marker.rect(0, 0, viewfinderIndent, marker.height); //left
		marker.rect(marker.width - viewfinderIndent, 0,viewfinderIndent, marker.height); //right
		marker.rect(0, marker.height - viewfinderIndent, marker.width, viewfinderIndent); //bottom

		//toggle
		marker.fill(225, 120, 120);
		marker.rect(toggle.x - 5, toggle.y - 5, toggle.width, toggle.height, toggleRadius);
		
		//toggle text
		marker.fill(0);
		marker.textSize(12);
		String str = "Switch to Portfolio";
		float w = marker.textWidth(str);
		marker.text(str, toggle.x+toggle.width/2-w/2 - 5, toggle.y+toggle.height/2);
		
		//shutter 
		marker.fill(120);
		marker.rect(shutterButton.x, shutterButton.y, shutterButton.width, shutterButton.height);
		
		//shutter text
		marker.fill(0);
		String shutterStr = "Shutter";
		//float w = marker.textWidth(str);
		marker.text(shutterStr, shutterButton.x + 5, shutterButton.y + 15);
		
		//key instruction text
		marker.fill(255);
		marker.textSize(15);
		String keyDefinitions = "Up/Down/Left/Right: WASD, Forward: UP, Back: DOWN, Spin: LEFT/RIGHT";
		marker.text(keyDefinitions, toggle.x, marker.height - 30);
		
		marker.popMatrix();
	}
	
	/**
	 * Checks for user interactions for toggling between screens and capturing a photo
	 * @param marker
	 * @pre DrawingSurface marker is not null
	 */
	public void mousePressed(DrawingSurface marker) {		
		Point p = new Point(marker.mouseX,marker.mouseY);
		if (toggle.contains(p)) {
			marker.switchScreen(1);
		}
		if (shutterButton.contains(p)) {
			shutter.screenshot(marker);
			SoundPlayer.playShutterSound();
		}
	}
}
