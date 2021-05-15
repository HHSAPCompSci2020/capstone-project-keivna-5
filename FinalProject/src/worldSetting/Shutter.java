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
	static ArrayList<PImage> images;
	
	/**
	 * Initializes the list of images
	 */
	public Shutter() {
		images = new ArrayList<PImage>();
	}

	/**
	 * Captures a screenshot of the screen, not including the viewfinder
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
	 * @return ArrayList<PImage> all images captured while the window is opened
	 */
	public static ArrayList<PImage> getallImages(){
		return images;
	}
}
