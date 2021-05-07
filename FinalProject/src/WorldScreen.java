
import processing.core.*;

import processing.opengl.*;
import worldSetting.Camera;
import worldSetting.Element;
import worldSetting.World;
import java.awt.event.KeyEvent;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import mazeexample.Maze;
import mazeexample.Player;


public class WorldScreen extends Screen {

	private ArrayList<Integer> keys = new ArrayList<Integer>();

	
	private Camera camera;

	//private DrawingSurface surface;
	private Rectangle button;
	private World world;
	

	public WorldScreen() {
		
		super(800,600);
		//this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
		
//		world = new World(surface);
		world = new World();
		camera = new Camera();

	}

	//TODO: draw world + menu + tabs here
	public void draw(DrawingSurface surface) {
		surface.pushStyle();
		surface.background(255,255,255);

//		surface.box(20);
		
		world.display(surface);
		camera.draw(surface);
		//world.update(camera);
//		player.draw(this);
		
		if (surface.checkKey(KeyEvent.VK_W))
			camera.moveZ(1);
		else if (surface.checkKey(KeyEvent.VK_S))
			camera.moveZ(-1);
		if (surface.checkKey(KeyEvent.VK_A))
			camera.moveX(1);
		else if (surface.checkKey(KeyEvent.VK_D))
			camera.moveX(-1);
		if (surface.checkKey(KeyEvent.VK_SPACE))
			camera.jump();

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

	public void update(Camera c) {
		//c.act(new Element());
	}

	//TODO: turn into tabs here
	public void mousePressed(DrawingSurface surface) {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN2);
	}
	
	public void setPlayerAtStart() {
//		camera.moveTo(start.getX(), start.getY()-15, start.getZ());
		camera.moveTo(350, 350, 50);

	}
	
	// Checks if given key code is in the array list
//	private boolean checkKey(int i) {
//		return keys.contains(i);
//	}


}

