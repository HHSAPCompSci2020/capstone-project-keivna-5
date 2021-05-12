package drawingPackage;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import portfolio.Portfolio;
import processing.core.PApplet;


public class PortfolioScreen extends Screen {
	
	private int x, y;
	public float ratioX, ratioY;

	private Rectangle button;

	
	private Portfolio portfolio;
	
	public PortfolioScreen() {
		super(800,600);
		
		x = 30;
		y = 30;
		
		button = new Rectangle(800/2-100,600/2-50,200,100);

		portfolio = new Portfolio();
	}
	
	//TODO: draw portfolio here
	public void draw(PApplet marker) {
		//System.out.println("drawing portfolio screen");

		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;
		
		// Draw stuff
		
		marker.pushStyle();
		
//		surface.background(255);   // Clear the screen with a white background
//		surface.stroke(0);     // Set line drawing color to white
//		surface.noFill();
//
//		surface.rect(x,y,30,30);
//		
//		surface.fill(0);
//		surface.text("Move: Arrow keys",10,30);
//		surface.text("Menu: Space",10,50);
		
		
		marker.background(0,0,0);
		marker.fill(120);

		marker.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		marker.fill(0);

		String str = "Switch to Viewfinder World";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		

		marker.popStyle();

		
		

	}
	
	//TODO: turn into tabs here
	public void mousePressed(DrawingSurface marker) {
		System.out.println("calling mouse pressed from portfolio");
		Point p = actualCoordinatesToAssumed(new Point(marker.mouseX,marker.mouseY));
		System.out.println("point p from portfolio: " + p);

		if (button.contains(p)) {
//			marker.switchScreen(ScreenSwitcher.SCREEN2);
			marker.switchScreen(0);

			System.out.println("switch screen");
		}
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	
}