package photography;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents the physical Shutter on the Viewfinder
 * @author Elise
 *
 */
public class Shutter {
	/**
	 * Captures a screenshot of the screen
	 * @param marker
	 */
	public PImage screenshot(PApplet marker, int index) {
		try {
			Robot robot = new Robot();
			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
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
	public PImage longExposureScreenshot(PApplet marker, int outerArrIndex, int innerArrIndex) {

		try {
			Robot robot = new Robot();	
			PImage shot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			//ex: 3rd photo in second arraylist of pimages (ie 2nd long expo shot) is in format "2.3.png"
			marker.saveFrame(outerArrIndex + "." + innerArrIndex + ".png");
			return shot;
		} catch (AWTException e) {
			return null;
		}
	}
}
