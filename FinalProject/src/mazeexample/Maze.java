package mazeexample;
import java.util.ArrayList;

import processing.core.*;

public class Maze {
	private Block[][] blocks;
	private Block start;

	ArrayList<Block> b = new ArrayList<Block>();
	
	public Maze(int size) {
		blocks = new Block[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				float x = i * 5;
				float y = 0;
				float z = j * 5;
				blocks[i][j] = new Block(x, y, z, 5);
				b.add(blocks[i][j]);
			}
		}
		start = blocks[0][0];
	}

	private double random(double lower, double upper) {
		return Math.random() * (upper - lower) + lower;
	}

	public void update(Player p) {
		p.act(b);
	}

	public void display(PApplet g) {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				blocks[i][j].display(g);
			}
		}
	}

	public void setPlayerAtStart(Player player) {
		player.moveTo(start.getX(), start.getY()-15, start.getZ());
	}
}