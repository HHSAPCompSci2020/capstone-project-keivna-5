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
	//PImage screenshot;
	boolean smoothOn;
	int x,y;
	PVector screen;
	
	static ArrayList<PImage> images;
	//static ArrayList<Integer> imageNames;
	
	public Shutter() {
		images = new ArrayList<PImage>();
		//imageNames = new ArrayList<Integer>();
	}

	public void setup(PApplet marker) {
//		marker.size(marker.width, marker.height);
//		marker.frame.removeNotify();
//		marker.save("photo-####.png");
//		marker.save("photo1.png");

	}

	//THIS ISN'T ACTUALLY CALLED!
	public static void draw(PApplet marker) {
		screenshot(marker);
//		marker.image(screenshot, 1000, 1000, marker.width/5, marker.height/5);
		
		//marker.image(screenshot, 0, 0, marker.width, marker.height);

	}

	public static void screenshot(PApplet marker) {
		try {
			Robot robot = new Robot();
			PImage screenshot = new PImage(robot.createScreenCapture(new Rectangle((int)Constants.viewfinderIndent,(int)Constants.viewfinderIndent,marker.width - (int)Constants.viewfinderIndent, marker.height - (int)Constants.viewfinderIndent)));
			images.add(screenshot);
			System.out.println("adding this image: " + screenshot);
			
//			int stringNum = 0;
//			if (imageNames.size() == 0) {
//				imageNames.add(0001);
//				stringNum = 0001;
//			}
//			else {
//				//get prev
//				int temp = imageNames.get(imageNames.size() - 1);
//				stringNum = temp+1;
//				imageNames.add(stringNum);
//				
//						//add 1 to prev
//						//set that to stringName
//						
//			}
			
			
			
			//last index represents photo num
			//ex: 
			int lastIndex = images.size() - 1;
			marker.saveFrame(lastIndex + ".png");
			
			//String stringNumToString = Integer.toString(stringNum);
			//images.add(stringNumToString + ".png");
			
		} catch (AWTException e) { }
		
	}

//	public PImage getScreenshot() {
//		return screenshot;
//	}
	
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
