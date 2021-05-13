package worldSetting;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.util.ArrayList;

import drawingPackage.Constants;
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

//	public void setup(PApplet marker) {
////		marker.size(marker.width, marker.height);
////		marker.frame.removeNotify();
////		marker.save("photo-####.png");
////		marker.save("photo1.png");
//	}

	public static void screenshot(PApplet marker) {
		try {
			Robot robot = new Robot();
			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(0,0,marker.width, marker.height)));
			
			//attempts to crop the image ahead of time
//			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle((int)Constants.viewfinderIndent,(int)Constants.viewfinderIndent,marker.width - (int)Constants.viewfinderIndent, marker.height - (int)Constants.viewfinderIndent)));
//			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(60,60,marker.width - 60, marker.height - 60)));
//			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle(100,100,200, 200)));

//			screenshot.resize(60, 60);
//			screenshot.copy(0, 0,  marker.width, marker.height, 60, 60, marker.width-60, marker.height-60);
			
			images.add(screenshot);
			
			//last index represents photo num
			int lastIndex = images.size() - 1;
			marker.saveFrame(lastIndex + ".png");
			
		} catch (AWTException e) { }
		
	}
	
	public static ArrayList<PImage> getallImages(){
		return images;
	}

	//	public void capture(PApplet marker) {
	//		//saves photos as photo-0001.png, photo-0002.png, photo-0003.png...
	//		marker.saveFrame("photo-####.png");
	//		
	//		marker.sketchDisplay();
	//	}
}
