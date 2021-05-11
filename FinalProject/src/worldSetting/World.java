package worldSetting;

import java.util.ArrayList;


//import mazeexample.Element;

//import mazeexample.Player;
import processing.core.*;

public class World { //build water, bridge
	
//	private Element[][] elements;
	ArrayList<Element> b = new ArrayList<Element>();
	private Bridge bridge;
	private Water water;
	
	private Element element;
	
	public World() {
		
		element = new Element(350, 350, 50, 50);
		b.add(element);
		
//		elements = new Element[size][size];
//
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				float x = i * 5;
//				float y = 0;
//				float z = j * 5;
//				elements[i][j] = new Element(x, y, z, 5);
//				b.add(elements[i][j]);
//			}
//		}
//		start = elements[0][0];
	}

//	private double random(double lower, double upper) {
//		return Math.random() * (upper - lower) + lower;
//	}

	public void display(PApplet g) {
		element.display(g);
		//bridge.display(marker);
		//water.display(marker);
	}

//	public void setPlayerAtStart(Camera player) {
//		player.moveTo(start.getX(), start.getY()-15, start.getZ());
//	}
	
	public void update(Camera p) {
		p.act(b);
	}
	
	public void setPlayerAtStart(Camera player) {
		player.moveTo(element.getX(), element.getY()-15, element.getZ());
	}

}
