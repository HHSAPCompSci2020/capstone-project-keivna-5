package drawingPackage;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import portfolio.Portfolio;
import processing.core.PApplet;


public class PortfolioScreen extends Screen {
	
	public float ratioX, ratioY;
	private Rectangle button;
	private Portfolio portfolio;
	
	public PortfolioScreen() {
		super(800,600);
		
		//button = new Rectangle(800/2-100,600/2-50,200,100);
		button = new Rectangle(25,25,120,20);
		portfolio = new Portfolio();
	}
	
	public void draw(PApplet marker) {
		
		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;
				
		marker.pushStyle();
		
		marker.background(0,0,0);
		marker.fill(120);

		portfolio.draw(marker);
		
		marker.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		marker.fill(0);
		marker.textSize(12);

		String str = "Switch to Viewfinder";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2 + 2, button.y+button.height/2 + 5);
		
		marker.popStyle();
	}
	
	public void mousePressed(DrawingSurface marker) {
		Point p = new Point(marker.mouseX,marker.mouseY);

		if (button.contains(p)) {
			marker.switchScreen(0);
		}
	}
}