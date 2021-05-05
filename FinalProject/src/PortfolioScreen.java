


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import portfolio.Portfolio;
import processing.core.PApplet;


public class PortfolioScreen extends Screen {
	
	private int x, y;
	
	private DrawingSurface surface;
	private Rectangle button;

	
	private Portfolio portfolio;
	
	public PortfolioScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		
		x = 30;
		y = 30;
		
		button = new Rectangle(800/2-100,600/2-50,200,100);

		portfolio = new Portfolio();
	}
	
	//TODO: draw portfolio here
	public void draw() {
		
		// Draw stuff
		
		surface.pushStyle();
		
//		surface.background(255);   // Clear the screen with a white background
//		surface.stroke(0);     // Set line drawing color to white
//		surface.noFill();
//
//		surface.rect(x,y,30,30);
//		
//		surface.fill(0);
//		surface.text("Move: Arrow keys",10,30);
//		surface.text("Menu: Space",10,50);
		
		
		surface.background(0,0,0);
		
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Click me!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		

		surface.popStyle();

		
		

	}
	
	//TODO: turn into tabs here
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN1);
	}
	
	
}