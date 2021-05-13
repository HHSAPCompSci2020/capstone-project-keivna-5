package worldSetting;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Shutter {
	boolean smoothOn;
	int x,y;
	PVector screen;
	
	static ArrayList<PImage> images;
	
	public Shutter() {
		images = new ArrayList<PImage>();
	}

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
	
	public static ArrayList<PImage> getallImages(){
		return images;
	}
}
