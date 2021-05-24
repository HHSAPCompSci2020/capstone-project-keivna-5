package worldSetting;

import java.awt.AWTException;

import java.awt.Rectangle;
import java.awt.Robot;
import java.util.ArrayList;

import portfolio.LongExpoPhoto;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the physical Shutter on the Viewfinder
 * @author elise
 *
 */
public class Shutter {

//	private static int prevSavedTime = 0;
//	private static ArrayList<PImage> images;

	//each ArrayList<PImage> is 1 long exposure image
//	private static ArrayList<ArrayList<PImage>> longExposureImages;
//	private static ArrayList<LongExpoPhoto> longExposureImages;

//	private static int longExpoSize;
//	private static ArrayList<Integer> currLongExpoSize; 
	/**
	 * Initializes the list of images
	 */
	public Shutter(PApplet marker) {
//		images = new ArrayList<PImage>();
//		longExposureImages = new ArrayList<ArrayList<PImage>>();
//		longExposureImages = new ArrayList<LongExpoPhoto>();

//		longExpoSize = 0;
//		currLongExpoSize = new ArrayList<Integer>();
	}

	/**
	 * Captures a screenshot of the screen
	 * @param marker
	 */
//	public static void screenshot(PApplet marker) {
	public PImage screenshot(PApplet marker, int index) {

		try {
			Robot robot = new Robot();
			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));


			//last index represents photo num
//			int lastIndex = images.size() - 1;
//			marker.saveFrame(lastIndex + ".png");
			marker.saveFrame(index + ".png");

			return screenshot;


		} catch (AWTException e) { 
			return null;
		}
	}

	/**
	 * Captures a long exposure screenshot of the screen
	 * @param marker
	 */
//	public static void longExposureScreenshot(PApplet marker, int outerArrIndex, int innerArrIndex) {
	public PImage longExposureScreenshot(PApplet marker, int outerArrIndex, int innerArrIndex) {

		try {
			Robot robot = new Robot();
//			ArrayList<PImage> currLongExpo;
			
//			//this arraylist already exists
//			if (outerArrIndex < longExpoSize) {
//				currLongExpo = longExposureImages.get(outerArrIndex);
//				
//			} else { //make a new array
//				currLongExpo = new ArrayList<PImage>();
//				longExpoSize++;
//				currLongExpoSize.add(1);
//			}
						
			PImage shot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			//ex: 3rd photo in second arraylist of pimages (ie 2nd long expo shot) is in format "2.3.png"
			marker.saveFrame(outerArrIndex + "." + innerArrIndex + ".png");
//			currLongExpo.add(shot);

//			longExposureImages.add(currLongExpo);
			return shot;

		} catch (AWTException e) {
			return null;
		}
	}

//	/**
//	 * @return ArrayList<PImage> all non-long exposure images captured while the window is opened
//	 */
//	public static ArrayList<PImage> getallImages(){
//		return images;
//	}
//
//	/**
//	 * @return ArrayList<ArrayList<PImage>> all long exposition images captured while the window is opened
//	 */
//	public static ArrayList<ArrayList<PImage>> getallLongExpoImages(){
//		return longExposureImages;
//	}
//	
//	/**
//	 * @return the number of long exposure images
//	 */
//	public static int longExpoSize() {
//		return longExpoSize;
//	}
}
