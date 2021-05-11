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
		
		//ratioX = (float)marker.width/marker.DRAWING_WIDTH;
		//ratioY = (float)marker.height/this.DRAWING_HEIGHT;
		
		//draw viewfinder
		marker.pushMatrix();

		
//		surface.background(255);   // Clear the screen with a white background
//		surface.stroke(0);     // Set line drawing color to white
//		surface.noFill();
//
//		surface.rect(x,y,30,30);
//		
//		surface.fill(0);
//		surface.text("Move: Arrow keys",10,30);
//		surface.text("Menu: Space",10,50);
		
		
		//marker.background(0,0,0);
		//marker.fill(0);
		
		//viewfinder black border
		marker.strokeWeight(0);
		marker.rect(0, 0, marker.width, 60); //top
		marker.rect(0, 0, 60, marker.height); //left
		marker.rect(marker.width - 60, 0, 60, marker.height); //side
		marker.rect(0, marker.height - 60, marker.width, 60); //other side

		//toggle
		marker.fill(225, 120, 120);
		marker.rect(button.x - 5, button.y - 5, button.width, button.height, 10, 10, 10, 10);
		
		//toggle text
		marker.fill(0);
		String str = "Switch to Portfolio";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2 - 5, button.y+button.height/2);
		
		marker.popMatrix();
	}
	
	public void mousePressed(DrawingSurface marker) {
		System.out.println("calling mouse pressed from viewfinder");
		
//		Point p = actualCoordinatesToAssumed(new Point(marker.mouseX,marker.mouseY));
		Point p = new Point(marker.mouseX,marker.mouseY);

		System.out.println("point p from viewfinder: " + p);
		if (button.contains(p)) {
			System.out.println("buttons contains p in viewfinder");

			marker.switchScreen(1);

		}
	}
	
	//TODO: THIS DOESN"T WORK!!
//	public Point assumedCoordinatesToActual(Point assumed) {
//		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
//	}
//
//	public Point actualCoordinatesToAssumed(Point actual) {
//		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
//	}
}
