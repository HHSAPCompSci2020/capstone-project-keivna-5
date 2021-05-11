package worldSetting;

import java.awt.Point;
import java.awt.Rectangle;

import drawingPackage.DrawingSurface;
//import onlyonescreen.DrawingSurface;
import processing.core.PApplet;

public class Viewfinder {
	private Rectangle button;
	public float ratioX, ratioY;

	public Viewfinder() {
//		button = new Rectangle(800/2-100,600/2-50,200,100);
		button = new Rectangle(50,50,100,100);

	}
	
	//TODO: draw viewfinder here
	public void draw(PApplet marker) {

		//ratioX = (float)marker.width/this.DRAWING_WIDTH;
		//ratioY = (float)marker.height/this.DRAWING_HEIGHT;
		
		// Draw stuff
		
		//marker.pushStyle();
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
		
		marker.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		marker.fill(0);
		String str = "viewfinder button";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		

//		marker.popStyle();
		marker.popMatrix();
	}
	
	public void mousePressed(DrawingSurface marker) {
		//System.out.println("calling mouse pressed from worldscreen");

		Point p = actualCoordinatesToAssumed(new Point(marker.mouseX,marker.mouseY));
		if (button.contains(p)) {
//			marker.switchScreen(ScreenSwitcher.SCREEN2);
			marker.switchScreen(1);

			//System.out.println("switch screen");
		}
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
}
