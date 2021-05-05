
import processing.core.*;
import processing.opengl.*;

import worldSetting.World;

import java.awt.Point;
import java.awt.Rectangle;


public class WorldScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle button;
	private World world;

	public WorldScreen(DrawingSurface surface) {
		
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
		
//		world = new World(surface);
		world = new World();
	}


	//TODO: draw world + menu + tabs here
	public void draw() {
		surface.pushStyle();
		surface.background(255,255,255);

		surface.box(20);
		
		//world.display(surface);

//		
//		//switch tabs
//		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
//		surface.fill(0);
//		String str = "Click me!";
//		float w = surface.textWidth(str);
//		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
//		
		surface.popStyle();
	}



	//TODO: turn into tabs here
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN2);
	}
	
	// Change stuff

//	if (surface.keyPressed(KeyEvent.VK_LEFT))
//		x -= 5;
//	if (surface.keyPressed(KeyEvent.VK_RIGHT))
//		x += 5;
//	if (surface.keyPressed(KeyEvent.VK_UP))
//		y -= 5;
//	if (surface.keyPressed(KeyEvent.VK_DOWN))
//		y += 5;


//	if (surface.keyPressed(KeyEvent.VK_SPACE)) {
//		surface.switchScreen(ScreenSwitcher.SCREEN1);
//	}
	

}

