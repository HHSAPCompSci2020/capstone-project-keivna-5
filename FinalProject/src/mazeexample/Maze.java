package mazeexample;
import java.util.ArrayList;

import processing.core.*;

public class Maze {
	private Element[][] elements;
	private Element start;

	ArrayList<Element> b = new ArrayList<Element>();
	
	public Maze(int size) {
		elements = new Element[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				float x = i * 5;
				float y = 0;
				float z = j * 5;
				elements[i][j] = new Element(x, y, z, 5);
				b.add(elements[i][j]);
			}
		}
		start = elements[0][0];
	}

	private double random(double lower, double upper) {
		return Math.random() * (upper - lower) + lower;
	}

	public void update(Player p) {
		p.act(b);
	}

	public void display(PApplet g) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[i].length; j++) {
				elements[i][j].display(g);
			}
		}
	}

	public void setPlayerAtStart(Player player) {
		player.moveTo(start.getX(), start.getY()-15, start.getZ());
	}
}