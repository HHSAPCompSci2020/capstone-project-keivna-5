package photography;

import java.awt.Point;
import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import worldSetting.SoundPlayer;
import worldSetting.World;
import worldSetting.World.BackgroundColor;

/**
 * Represents the 2D aspect of the World, where photos are taken and settings are changed
 * @author Elise
 */
public class Viewfinder {

	private Rectangle shutterButton, longExpoShutterButton;
	private Shutter shutter;
	
	private static ArrayList<LongExpoPhoto> longExposureImages;
	private static ArrayList<PImage> images;

	private Rectangle ISOup, ISOdown, shutterSpeedUp, shutterSpeedDown, lightSourceUp, lightSourceDown, lightSourceLeft, lightSourceRight;
	private final int[] ISOvalues = {100, 200, 400, 800, 1600, 3200, 6400};
	private int ISOindex;

	private final static int[] shutterSpeedValues = {3, 5, 10, 20};
	private int shutterSpeedIndex;
	
	private double lightSourceX, lightSourceY; //from 0-1, to be multiplied by marker.width or height in world draw

	private World.BackgroundColor backgroundColor;
	private Rectangle[] backgroundSquares;
	private PImage[] backgroundImages;

	private static final double longExpoSpeedFactor = 4;
	private static final float viewfinderIndent = 60;

	/**
	 * Initializes fields
	 */
	public Viewfinder(PApplet marker, int screenWidth) {
		shutterButton = new Rectangle(620, 30, 120, 30);
		longExpoShutterButton = new Rectangle(420, 30, 120, 30);

		shutter = new Shutter();	
		ISOup = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*1, 30, 30);
		ISOdown = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*2 + 15, 30, 30);

		shutterSpeedUp = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*3.5), 30, 30);
		shutterSpeedDown = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*4.75) + 15, 30, 30);

		lightSourceUp = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)viewfinderIndent*7, 30, 30);
		lightSourceDown = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*7.5) + 10, 30, 30);

		lightSourceLeft = new Rectangle (screenWidth - (int)(3*viewfinderIndent/4), (int)(viewfinderIndent*8.5), 30, 30);
		lightSourceRight = new Rectangle (screenWidth - (int)(viewfinderIndent/4) - 10, (int)(viewfinderIndent*8.5), 30, 30);

		images = new ArrayList<PImage>();
		longExposureImages = new ArrayList<LongExpoPhoto>();

		lightSourceX = 0.5;
		lightSourceY = 0.5;

		shutterSpeedIndex = 0;
		ISOindex = 3;

		backgroundColor = BackgroundColor.CLEAR_DAY;

		backgroundSquares = new Rectangle[BackgroundColor.values().length];
		backgroundImages = new PImage[BackgroundColor.values().length];

		//creates rectangles for images w dimensions
		for (int i = 0; i < backgroundSquares.length; i++) {
			backgroundSquares[i] = new Rectangle ((int)(viewfinderIndent/4), (int)(viewfinderIndent + (viewfinderIndent*i)), (int)(viewfinderIndent/2), (int)(viewfinderIndent/2));
		}
	}

	/**
	 * Draws the 2D border ViewFinder surrounding the world as well as all buttons that make it up
	 * @param PApplet marker
	 * @pre marker can't be null
	 * @post the PApplet marker will change
	 */
	public void draw(PApplet marker) {			
		//fill up background Images
		for (int i = 0; i < backgroundImages.length; i++) {			
			String imageName = "media/cropped-sky-";
			switch (i) {
			case 0:
				imageName += "day-clear.png";
				break;
			case 1:
				imageName += "day-cloudy.png";
				break;
			case 2:
				imageName += "night-cloudy.png";
				break;
			case 3:
				imageName += "night-sunset.png";
				break;
			case 4:
				imageName += "night-stars.png";
				break;
			case 5:
				imageName += "pure-black.png";
				break;
			}
			backgroundImages[i] = marker.loadImage(imageName);
		}
		
		marker.pushMatrix();		
		marker.fill(0);
		marker.hint(marker.DISABLE_DEPTH_TEST);

		//viewfinder black border
		marker.strokeWeight(0);
		marker.rect(0, 0, marker.width, viewfinderIndent); //top
		marker.rect(0, 0, viewfinderIndent, marker.height); //left
		marker.rect(marker.width - viewfinderIndent, 0,viewfinderIndent, marker.height); //right
		marker.rect(0, marker.height - viewfinderIndent, marker.width, viewfinderIndent); //bottom

		//shutter 
		marker.fill(120);
		marker.rect(shutterButton.x, shutterButton.y, shutterButton.width, shutterButton.height);

		//shutter text
		marker.fill(0);
		marker.textSize(12);
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
		String keyDefinitions = "Up/Down/Left/Right: WASD, Forward/Backward: Q/E, Pan: UP/DOWN/LEFT/RIGHT";
		marker.text(keyDefinitions, viewfinderIndent, marker.height - 30);

		//ISO up triangle
		marker.triangle(ISOup.x, ISOup.y + ISOup.height, ISOup.x + ISOup.width, ISOup.y + ISOup.height, ISOup.x + (ISOup.width/2), ISOup.y);

		//ISO text
		marker.text("ISO", ISOdown.x + 3, ISOdown.y - (viewfinderIndent/4) - 10);
		marker.text(getISOvalue(), ISOdown.x, ISOdown.y - (viewfinderIndent/8));

		//ISO down triangle
		marker.triangle(ISOdown.x, ISOdown.y, ISOdown.x + ISOdown.width, ISOdown.y, ISOdown.x + (ISOdown.width/2), ISOdown.y + ISOdown.height);


		//Shutter speed up triangle
		marker.triangle(shutterSpeedUp.x, shutterSpeedUp.y + shutterSpeedUp.height, shutterSpeedUp.x + shutterSpeedUp.width, shutterSpeedUp.y + shutterSpeedUp.height, shutterSpeedUp.x + (shutterSpeedUp.width/2), shutterSpeedUp.y);

		//Shutter speed text
		marker.text("Shutter", shutterSpeedDown.x - 8, shutterSpeedDown.y - (viewfinderIndent/4) - 25);
		marker.text("Speed", shutterSpeedDown.x - 5, shutterSpeedDown.y - (viewfinderIndent/4) - 10);
		int actualShutterSpeed = shutterSpeedValues[shutterSpeedIndex];
		marker.text(actualShutterSpeed, shutterSpeedDown.x + 8, shutterSpeedDown.y - (viewfinderIndent/8));

		//Shutter speed down triangle
		marker.triangle(shutterSpeedDown.x, shutterSpeedDown.y, shutterSpeedDown.x + shutterSpeedDown.width, shutterSpeedDown.y, shutterSpeedDown.x + (shutterSpeedDown.width/2), shutterSpeedDown.y + shutterSpeedDown.height);

		//lighting text
		marker.text("light", lightSourceUp.x - (lightSourceUp.width/4), lightSourceUp.y - lightSourceUp.height);
		marker.text("source", lightSourceUp.x - (lightSourceUp.width/4), lightSourceUp.y - (lightSourceUp.height/2));		

		//light source up triangle
		marker.triangle(lightSourceUp.x, lightSourceUp.y + lightSourceUp.height, lightSourceUp.x + lightSourceUp.width, lightSourceUp.y + lightSourceUp.height, lightSourceUp.x + (lightSourceUp.width/2), lightSourceUp.y);

		//light source down triangle
		marker.triangle(lightSourceDown.x, lightSourceDown.y, lightSourceDown.x + lightSourceDown.width, lightSourceDown.y, lightSourceDown.x + (lightSourceDown.width/2), lightSourceDown.y + lightSourceDown.height);

		//points plotted in counterclockwise direction starting from very left
		//light source left triangle
		marker.triangle(lightSourceLeft.x - (lightSourceLeft.width/4), lightSourceLeft.y + (lightSourceLeft.height/2), lightSourceLeft.x + (lightSourceLeft.width/2), lightSourceLeft.y + lightSourceLeft.height, lightSourceLeft.x + (lightSourceLeft.width/2), lightSourceLeft.y);

		//light source right triangle
		marker.triangle(lightSourceRight.x, lightSourceRight.y, lightSourceRight.x, lightSourceRight.y + lightSourceRight.height, lightSourceRight.x + (3*lightSourceRight.width/4), lightSourceRight.y + (lightSourceRight.height/2));

		//squares to change background		
		for (int i = 0; i < backgroundSquares.length; i++) {
			PImage backgroundImg = backgroundImages[i];
			String backgroundDisplayed = backgroundColor.toString();
			 //if background being displayed rn is one in loop, make border red
			if (BackgroundColor.valueOf(backgroundDisplayed).ordinal() == i) {
				marker.fill(255, 0, 0);
			}
			int borderExtra = 2; //how much farther the border goes past the background image
			//draws rect a bit larger to make border
			marker.rect(backgroundSquares[i].x - borderExtra, backgroundSquares[i].y - borderExtra, backgroundSquares[i].width + (2*borderExtra), backgroundSquares[i].height + (2*borderExtra));

			marker.fill(255); //reset to white
			marker.image(backgroundImg, backgroundSquares[i].x, backgroundSquares[i].y);
		}
		
		marker.hint(marker.ENABLE_DEPTH_TEST);
		marker.popMatrix();
	}

	/**
	 * Checks for user interactions for capturing single shot, long exposure, and changing camera settings
	 * @param marker
	 * @pre DrawingSurface marker is not null
	 */
	public void mousePressed(DrawingSurface marker) {		
		Point p = new Point(marker.mouseX,marker.mouseY);

		if (shutterButton.contains(p)) {
			images.add(shutter.screenshot(marker, images.size()));
			SoundPlayer.playShutterSound();
		}

		else if (longExpoShutterButton.contains(p)) {
//			SoundPlayer.playShutterSound();
			LongExpoPhoto longExpoPhotos = new LongExpoPhoto(shutterSpeedValues[shutterSpeedIndex]);
			
			int outerArrIndex = longExposureImages.size(); //for shutter to see if it needs to make a new long expo arraylist or not
			//represents the number of screenshots
			for (int i = 0; i < (shutterSpeedValues[shutterSpeedIndex] * longExpoSpeedFactor); i++) {
				SoundPlayer.playShutterSound();

				//draw in between screenshots to get long exposure effect
				marker.draw();
				PImage photo = shutter.longExposureScreenshot(marker, outerArrIndex, i);
				longExpoPhotos.addPhoto(photo);
			}
			
			longExposureImages.add(longExpoPhotos);
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

		else if (shutterSpeedUp.contains(p)) {
			if (shutterSpeedIndex < shutterSpeedValues.length - 1) {
				shutterSpeedIndex++;
			}
		}

		else if (shutterSpeedDown.contains(p)) {
			if (shutterSpeedIndex > 0) {
				shutterSpeedIndex--;
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
		for (int i = 0; i < backgroundSquares.length; i++) {
			if (backgroundSquares[i].contains(p)) {
				backgroundColor = BackgroundColor.values()[i];
			}
		}
	}

	/**
	 * @return current ISO value
	 */
	public int getISOvalue() {
		return ISOvalues[ISOindex];
	}

	/**
	 * @return y-value of the light source
	 */
	public double getLightSourceY() {
		return lightSourceY;
	}
	
	/**
	 * @return x-value of the light source
	 */
	public double getLightSourceX() {
		return lightSourceX;
	}
	
	/**
	 * @return enum value of the current background color
	 */
	public BackgroundColor getBackgroundEnum() {
		return backgroundColor;
	}
	
	/**
	 * @return an array list of all single shot images that have been taken
	 */
	public ArrayList<PImage> getAllSingleShotPhotos(){
		return images;
	}
	
	/**
	 * @return an array list of all the long expoure images that have been taken
	 */
	public ArrayList<LongExpoPhoto> getAllLongExpoImages(){
		return longExposureImages;
	}
}
