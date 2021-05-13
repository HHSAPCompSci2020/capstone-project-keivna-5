package drawingPackage;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import portfolio.Portfolio;
import processing.core.PApplet;


public class PortfolioScreen extends Screen {
	
	public float ratioX, ratioY;
	private Rectangle toggle;
	private Portfolio portfolio;
	
	public PortfolioScreen() {
		super(800,600);
		
		//button = new Rectangle(800/2-100,600/2-50,200,100);
		toggle = new Rectangle(Constants.toggleX,Constants.toggleY,Constants.toggleWidth,Constants.toggleHeight);
		portfolio = new Portfolio();
	}
	
	public void draw(PApplet marker) {
		
		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;
				
		marker.pushStyle();
		
		marker.background(0,0,0);
		marker.fill(120);

		portfolio.draw(marker);
		
		//toggle
		marker.rect(toggle.x - 5, toggle.y - 5, toggle.width, toggle.height, Constants.toggleRadius);
		
		//toggle text
		marker.fill(0);
		marker.textSize(12);
		String str = "Switch to Viewfinder";
		float w = marker.textWidth(str);
		marker.text(str, toggle.x+toggle.width/2-w/2 - 5, toggle.y+toggle.height/2);
		
		marker.popStyle();
	}
	
	public void mousePressed(DrawingSurface marker) {
		Point p = new Point(marker.mouseX,marker.mouseY);

		if (toggle.contains(p)) {
			marker.switchScreen(0);
		}
	}
}