package worldSetting;

import java.awt.Point;
import java.awt.Rectangle;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;

public class Viewfinder {
	private Rectangle button;
	public float ratioX, ratioY;

	public Viewfinder() {
//		button = new Rectangle(800/2-100,600/2-50,200,100);
		button = new Rectangle(25,25,120,20);

	}
	
	public void draw(PApplet marker) {
				
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
		marker.rect(button.x - 5, button.y - 5, button.width, button.height, 10, 10, 10, 10);
		
		//toggle text
		marker.fill(0);
		marker.textSize(12);
		String str = "Switch to Portfolio";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2 - 5, button.y+button.height/2);
		
		marker.fill(255);
		marker.textSize(15);
		String keyDefinitions = "Up/Down/Left/Right: WASD, Forward: Q, Back: E";
		marker.text(keyDefinitions, button.x, marker.height - 30);
		
		marker.popMatrix();
	}
	
	public void mousePressed(DrawingSurface marker) {		
		Point p = new Point(marker.mouseX,marker.mouseY);
		if (button.contains(p)) {
			marker.switchScreen(1);
		}
	}
}
