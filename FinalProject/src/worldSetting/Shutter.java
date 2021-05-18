package worldSetting;

import java.awt.AWTException;

import java.awt.Rectangle;
import java.awt.Robot;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the physical Shutter on the Viewfinder
 * @author elise
 *
 */
public class Shutter {
	//boolean smoothOn;
	//int x,y;	
	
	private static ArrayList<PImage> images;
	
	//each ArrayList<PImage> is 1 long exposure image
	private static ArrayList<ArrayList<PImage>> longExposureImages;
	int savedTime;
	int totalTime = 60000;
	
	/**
	 * Initializes the list of images
	 */
	public Shutter(PApplet marker) {
		images = new ArrayList<PImage>();
		longExposureImages = new ArrayList<ArrayList<PImage>>();
		
		savedTime = marker.millis();

	}

	/**
	 * Captures a screenshot of the screen
	 * @param marker
	 */
	public static void screenshot(PApplet marker) {
		try {
			Robot robot = new Robot();
			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			
			images.add(screenshot);
			
			//last index represents photo num
			int lastIndex = images.size() - 1;
			marker.saveFrame(lastIndex + ".png");
			
		} catch (AWTException e) { }
	}
	
	/**
	 * Captures a long exposure screenshot of the screen
	 * @param marker
	 */
	public static void longExposureScreenshot(PApplet marker) {
		try {
			Robot robot = new Robot();
			ArrayList<PImage> currLongExpo = new ArrayList<PImage>();
			int allLongExpoIndex = longExposureImages.size();
			
			int currIndex = 0;
			PImage screenshot0 = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			marker.saveFrame(allLongExpoIndex + "." + currIndex + ".png");

			currIndex = 1;
			PImage screenshot1 = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			marker.saveFrame(allLongExpoIndex + "." + currIndex + ".png");

			currIndex = 2;
			PImage screenshot2 = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			marker.saveFrame(allLongExpoIndex + "." + currIndex + ".png");

			currLongExpo.add(screenshot0);
			currLongExpo.add(screenshot1);
			currLongExpo.add(screenshot2);

			//add arraylist of images to longExposureImages
			longExposureImages.add(currLongExpo);
			
//			images.add(screenshot1);
			
			/* saved in format "a.b"
			 	a is longExposureImages index
			 	b is currLongExpo index
			 	ex: second image in first long expo array would be 1.2
			 */
			
			//last index represents photo num
			int lastIndex = longExposureImages.size() - 1;
			marker.saveFrame(lastIndex + ".png");
			
		} catch (AWTException e) { }
	}
	
	//DO NOT DELETE!!! IDC HOW DESPERATELY YOU WANT TO DONT.
//	// Calculate how much time has passed
//	int passedTime = g.millis() - savedTime;
//	//the time is a factor of the duration of sea sound, play sound again
//	//currenlty doesn't work but it's fine bc sound plays for 4 minutes when window is opened
//	if (passedTime % Constants.seaSoundDurationMillis == 0) {
//		SoundPlayer.playSeaSound();
//
//		//System.out.println("1 minute has passed!");
//		//background(random(255)); // Color a new background
//		savedTime = g.millis(); // Save the current time to restart the timer!
	
	/**
	 * @return ArrayList<PImage> all non-long exposure images captured while the window is opened
	 */
	public static ArrayList<PImage> getallImages(){
		return images;
	}
	
	/**
	 * @return ArrayList<ArrayList<PImage>> all long exposition images captured while the window is opened
	 */
	public static ArrayList<ArrayList<PImage>> getallLongExpoImages(){
		return longExposureImages;
	}
}
