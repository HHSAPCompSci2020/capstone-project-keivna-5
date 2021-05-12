package worldSetting;

import java.awt.Point;
import java.awt.Rectangle;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Viewfinder {
	private Rectangle toggle, shutterButton;
	public float ratioX, ratioY;
	private Shutter shutter;

	public Viewfinder() {
		toggle = new Rectangle(25,25,120,20);
		shutterButton = new Rectangle(620, 30, 120, 30);

		shutter = new Shutter();
	}
	
	public void draw(PApplet marker) {
		//Rectangle toggle, shutterButton;
		
//		button = new Rectangle(800/2-100,600/2-50,200,100);

		//toggle = new Rectangle(25,25,120,20);
		//shutterButton = new Rectangle(marker.width - 180, 30, 120, 30);
		
		//draw viewfinder
		marker.pushMatrix();
		
		marker.fill(0);
		
		//viewfinder black border
		marker.strokeWeight(0);
		marker.rect(0, 0, marker.width, 60); //top
		marker.rect(0, 0, 60, marker.height); //left
		marker.rect(marker.width - 60, 0, 60, marker.height); //right
		marker.rect(0, marker.height - 60, marker.width, 60); //bottom

		//toggle
		marker.fill(225, 120, 120);
		marker.rect(toggle.x - 5, toggle.y - 5, toggle.width, toggle.height, 10);
		
		//toggle text
		marker.fill(0);
		marker.textSize(12);

		String str = "Switch to Portfolio";
		float w = marker.textWidth(str);
		marker.text(str, toggle.x+toggle.width/2-w/2 - 5, toggle.y+toggle.height/2);
		
		//shutter 
		marker.fill(120);
		marker.rect(shutterButton.x, shutterButton.y, shutterButton.width, shutterButton.height);
		
		//shutter text
		marker.fill(0);
		String shutterStr = "Shutter";
		//float w = marker.textWidth(str);
		marker.text(shutterStr, shutterButton.x + 5, shutterButton.y + 15);
		
		
		//key instruction text
		marker.fill(255);
		marker.textSize(15);
		String keyDefinitions = "Up/Down/Left/Right: WASD, Forward: Q, Back: E";
		marker.text(keyDefinitions, toggle.x, marker.height - 30);
		
		marker.popMatrix();
	}
	
	public void mousePressed(DrawingSurface marker) {		
		Point p = new Point(marker.mouseX,marker.mouseY);
		if (toggle.contains(p)) {
			marker.switchScreen(1);
		}
		if (shutterButton.contains(p)) {
			System.out.println("shutter capture");
			shutter.draw(marker);
			//shutter.capture(marker);
			PImage image = shutter.getScreenshot();
		}
		
	}
}
