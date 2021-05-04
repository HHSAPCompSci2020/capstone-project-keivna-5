import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.*;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
	}
	
	public void settings() {
		//fullScreen(P3D);
		super.size(800, 500, P3D);
		 
	}

	public void setup() {
		
		strokeWeight(2);
		this.frameRate(1000);
//		player = new Player();
//		player.setup(this);
//		maze = new Maze(20);
//		maze.setPlayerAtStart(player);
		
	}

	public void draw() {
		
		noCursor();
		background(51);
//		maze.display(this);
//		maze.update(player);
//		player.draw(this);
//
//		if (checkKey(KeyEvent.VK_W))
//			player.moveZ(1);
//		else if (checkKey(KeyEvent.VK_S))
//			player.moveZ(-1);
//		if (checkKey(KeyEvent.VK_A))
//			player.moveX(1);
//		else if (checkKey(KeyEvent.VK_D))
//			player.moveX(-1);
		

	}

	public void keyPressed() {
//		if (!checkKey(keyCode))
//			keys.add(keyCode);
//
//		if (checkKey(KeyEvent.VK_SPACE))
//			player.jump();
	}

	// Removes key from array list
//	public void keyReleased() {
//		while (checkKey(keyCode))
//			keys.remove(new Integer(keyCode));
//	}
//
//	// Checks if given key code is in the array list
//	private boolean checkKey(int i) {
//		return keys.contains(i);
//	}

}
