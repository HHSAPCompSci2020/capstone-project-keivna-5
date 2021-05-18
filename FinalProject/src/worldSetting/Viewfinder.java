package worldSetting;

import java.awt.Point;
import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the 2D aspect of the World, where photos are taken and settings are changed
 * @author elise
 */
public class Viewfinder {

	private Rectangle toggle, shutterButton, longExpoShutterButton;
	private Shutter shutter;
	private Rectangle ISOup, ISOdown, lightSourceUp, lightSourceDown, lightSourceLeft, lightSourceRight;
	private final int[] ISOvalues = {100, 200, 400, 800, 1600, 3200, 6400};
	private int ISOindex;
	private double lightSourceX, lightSourceY, lightSourceZ; //from 0-1, to be multiplied by marker.width or height in world draw
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
		longExpoShutterButton = new Rectangle(420, 30, 120, 30);
		
		shutter = new Shutter();	
		ISOup = new Rectangle (800 - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*2, 30, 30);
		ISOdown = new Rectangle (800 - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*3 + 15, 30, 30);
		
		lightSourceUp = new Rectangle (800 - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*5, 30, 30);
		lightSourceDown = new Rectangle (800 - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*5.5) + 10, 30, 30);

		lightSourceLeft = new Rectangle (800 - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*6.5), 30, 30);
		lightSourceRight = new Rectangle (800 - (int)(viewfinderIndent/4) - 10, (int)(viewfinderIndent*6.5), 30, 30);
		
		lightSourceX = 0.5;
		lightSourceY = 0.5;
		lightSourceZ = 0.5;

		ISOindex = 3;
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
		
		//long expo shutter 
		marker.fill(120);
		marker.rect(longExpoShutterButton.x, longExpoShutterButton.y, longExpoShutterButton.width, longExpoShutterButton.height);

		//shutter text
		marker.fill(0);
		String longExpoShutterStr = "Long Expo Shutter";
		//float w = marker.textWidth(str);
		marker.text(longExpoShutterStr, longExpoShutterButton.x + 5, longExpoShutterButton.y + 15);

		//key instruction text
		marker.fill(255);
		marker.textSize(15);
		String keyDefinitions = "Up/Down/Left/Right: WASD, Forward: UP, Back: DOWN, Spin: LEFT/RIGHT";
		marker.text(keyDefinitions, toggle.x, marker.height - 30);

		//ISO up triangle
//		marker.fill(0, 0, 255);
		marker.triangle(ISOup.x, ISOup.y + ISOup.height, ISOup.x + ISOup.width, ISOup.y + ISOup.height, ISOup.x + (ISOup.width/2), ISOup.y);

		//ISO text
		marker.text("ISO", ISOdown.x + 3, ISOdown.y - (viewfinderIndent/4) - 10);
		marker.text(getISOvalue(), ISOdown.x, ISOdown.y - (viewfinderIndent/8));

		//ISO down triangle
		marker.triangle(ISOdown.x, ISOdown.y, ISOdown.x + ISOdown.width, ISOdown.y, ISOdown.x + (ISOdown.width/2), ISOdown.y + ISOdown.height);
		
		//lighting text
//		marker.text("light \nsource", lightSourceUp.x - (lightSourceUp.width/4), lightSourceUp.y - lightSourceUp.height);		
		marker.text("light", lightSourceUp.x - (lightSourceUp.width/4), lightSourceUp.y - lightSourceUp.height);
		marker.text("source", lightSourceUp.x - (lightSourceUp.width/4), lightSourceUp.y - (lightSourceUp.height/2));		

		//light source up triangle
		marker.triangle(lightSourceUp.x, lightSourceUp.y + lightSourceUp.height, lightSourceUp.x + lightSourceUp.width, lightSourceUp.y + lightSourceUp.height, lightSourceUp.x + (lightSourceUp.width/2), lightSourceUp.y);
		
		//light source down triangle
		marker.triangle(lightSourceDown.x, lightSourceDown.y, lightSourceDown.x + lightSourceDown.width, lightSourceDown.y, lightSourceDown.x + (lightSourceDown.width/2), lightSourceDown.y + lightSourceDown.height);

		//points plotted in counterclockwise direction starting from very left
		//light source left triangle
//		marker.rect(lightSourceLeft.x, lightSourceLeft.y, lightSourceLeft.width, lightSourceLeft.height);
		marker.triangle(lightSourceLeft.x - (lightSourceLeft.width/4), lightSourceLeft.y + (lightSourceLeft.height/2), lightSourceLeft.x + (lightSourceLeft.width/2), lightSourceLeft.y + lightSourceLeft.height, lightSourceLeft.x + (lightSourceLeft.width/2), lightSourceLeft.y);
		
		//light source right triangle
//		marker.rect(lightSourceRight.x, lightSourceRight.y, lightSourceRight.width, lightSourceRight.height);
		marker.triangle(lightSourceRight.x, lightSourceRight.y, lightSourceRight.x, lightSourceRight.y + lightSourceRight.height, lightSourceRight.x + (3*lightSourceRight.width/4), lightSourceRight.y + (lightSourceRight.height/2));
		
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
		
		else if (shutterButton.contains(p)) {
			shutter.screenshot(marker);
			SoundPlayer.playShutterSound();
		}
		
		else if (longExpoShutterButton.contains(p)) {
			shutter.longExposureScreenshot(marker);
			SoundPlayer.playShutterSound();
		}
		
		else if (ISOup.contains(p)) {
			if (ISOindex < ISOvalues.length - 1) {
				ISOindex++;
			}
		}
		
		else if (ISOdown.contains(p)) {
			if (ISOindex > 0) {
				ISOindex--;
			}
		}
		
		else if (lightSourceUp.contains(p)) {
			if (lightSourceY < 1) {
				lightSourceY+= 0.1;
			}
			if (lightSourceY > 1) {
				lightSourceY = 1;
			}
		}
		
		else if (lightSourceDown.contains(p)) {
			if (lightSourceY > 0) {
				lightSourceY-= 0.1;
			} 
			if (lightSourceY < 0) {
				lightSourceY = 0;
			}
		}
		
		else if (lightSourceLeft.contains(p)) {
			if (lightSourceX < 1) {
				lightSourceX+= 0.1;
			}
			if (lightSourceX > 1) {
				lightSourceX = 1;
			}
		}
		
		else if (lightSourceRight.contains(p)) {
			if (lightSourceX > 0) {
				lightSourceX-= 0.1;
			} 
			if (lightSourceX < 0) {
				lightSourceX = 0;
			}
		}
	}
	
	public int getISOvalue() {
		return ISOvalues[ISOindex];
	}
	
	public double getLightSourceY() {
		return lightSourceY;
	}
	
	public double getLightSourceX() {
		return lightSourceX;
	}
	
	public double getLightSourceZ() {
		return lightSourceZ;
	}
}
