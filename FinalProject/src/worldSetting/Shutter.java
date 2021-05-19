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

	static int prevSavedTime = 0;

	private static ArrayList<PImage> images;

	//each ArrayList<PImage> is 1 long exposure image
	private static ArrayList<ArrayList<PImage>> longExposureImages;


	/**
	 * Initializes the list of images
	 */
	public Shutter(PApplet marker) {
		images = new ArrayList<PImage>();
		longExposureImages = new ArrayList<ArrayList<PImage>>();
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
	public static void longExposureScreenshot(PApplet marker, int outerArrIndex, int innerArrIndex) {
		try {
			Robot robot = new Robot();
			
			
			ArrayList<PImage> currLongExpo;
			
			//this arraylist already exists
			if (outerArrIndex < longExposureImages.size()) {
				currLongExpo = longExposureImages.get(outerArrIndex);
			} else {
				currLongExpo = new ArrayList<PImage>();

			}
			
//			int allLongExpoIndex = longExposureImages.size();			

			PImage shot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			marker.saveFrame(outerArrIndex + "." + innerArrIndex + ".png");
			currLongExpo.add(shot);



			//			long startTime = System.currentTimeMillis();
			//long elapsedTime = System.currentTimeMillis() - startTime;

			//shutter speed is 1 second
			//TODO: make shutter speed variablized for user to change
			//			int index = 0;
			//			while (System.currentTimeMillis() - startTime < 1000) { 
			//				long elapsedTime = System.currentTimeMillis() - startTime;
			//				PImage shot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			//				marker.saveFrame(allLongExpoIndex + "." + index + ".png");
			//				currLongExpo.add(shot);
			//
			//				index++;
			//				System.out.println("curr time - start time: " +elapsedTime);
			//			}

			longExposureImages.add(currLongExpo);

		} catch (AWTException e) { }
	}

	public int getOuterArrIndex() {
		return longExposureImages.size();		
	}

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
