package worldSetting;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Shutter {
	PImage screenshot;
	boolean smoothOn;
	int x,y;
	PVector screen;

	public Shutter() {

	}

	public void setup(PApplet marker) {
		marker.size(1632, 918);
		marker.frame.removeNotify();
		marker.save("photo-####.png");
//		marker.save("photo1.png");

	}

	public void draw(PApplet marker) {
		screenshot();
		marker.image(screenshot, 0, 0, marker.width, marker.height);
		

	}

	public void screenshot() {
		try {
			Robot robot = new Robot();
			screenshot = new PImage(robot.createScreenCapture(new Rectangle(0,0,1920, 1080)));
		} catch (AWTException e) { }
	}

	public PImage getScreenshot() {
		return screenshot;
	}

	//	public void capture(PApplet marker) {
	//		//saves photos as photo-0001.png, photo-0002.png, photo-0003.png...
	//		marker.saveFrame("photo-####.png");
	//		
	//		marker.sketchDisplay();
	//	}
}
