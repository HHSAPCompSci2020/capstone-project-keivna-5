package mazeexample;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.*;

public class MazeRunner extends PApplet {
	private ArrayList<Integer> keys = new ArrayList<Integer>();

	private Player player;
	private Maze maze;

	public static void main(String[] args) {
		PApplet.main("MazeRunner");
	}

	public void settings() {
		fullScreen(P3D);
		
		
		//super.size(300, 200, P3D);
		 
	}

	public void setup() {
		
		strokeWeight(2);
		this.frameRate(1000);
		player = new Player();
		player.setup(this);
		maze = new Maze(20);
		maze.setPlayerAtStart(player);
		
	}

	public void draw() {
		
		noCursor();
		background(51);
		maze.display(this);
		maze.update(player);
		player.draw(this);

		if (checkKey(KeyEvent.VK_W))
			player.moveZ(1);
		else if (checkKey(KeyEvent.VK_S))
			player.moveZ(-1);
		if (checkKey(KeyEvent.VK_A))
			player.moveX(1);
		else if (checkKey(KeyEvent.VK_D))
			player.moveX(-1);
		

	}

	public void keyPressed() {
		if (!checkKey(keyCode))
			keys.add(keyCode);

		if (checkKey(KeyEvent.VK_SPACE))
			player.jump();
	}

	// Removes key from array list
	public void keyReleased() {
		while (checkKey(keyCode))
			keys.remove(new Integer(keyCode));
	}

	// Checks if given key code is in the array list
	private boolean checkKey(int i) {
		return keys.contains(i);
	}
}