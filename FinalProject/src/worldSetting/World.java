package worldSetting;

import java.util.ArrayList;

//import mazeexample.Element;
//import mazeexample.Player;
import processing.core.PApplet;

public class World { //build water, bridge
	
//	private Element[][] elements;
//	private Element start;
//
//	ArrayList<Element> b = new ArrayList<Element>();
	
	private Element element;
	
	public World() {
		
		element = new Element(350, 350, 50, 50);
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

//	public void update(Camera p) {
//		p.act(b);
//	}

	public void display(PApplet g) {
		element.display(g);
		
//		for (int i = 0; i < elements.length; i++) {
//			for (int j = 0; j < elements[i].length; j++) {
//				elements[i][j].display(g);
//			}
//		}
	}

//	public void setPlayerAtStart(Camera player) {
//		player.moveTo(start.getX(), start.getY()-15, start.getZ());
//	}
	
//	private Bridge bridge;
//	private Water water;
//	private PApplet marker;
//	private Element element;
//	
//	public World(PApplet marker) {
//		element = new Element(0, 0, 0, 4);
//
//		element = new Element(5, 0, 3, 4);
//		//bridge = new Bridge(5, 5, 5, 10);
//		//water = new Water(12, 12, 12, 8);
//		this.marker = marker;
//	}
//	
//	public void display() {
//		//bridge.display(marker);
//		//water.display(marker);
//		element.display(marker);
//	}
	
//	public void update(Camera p) {
//		p.act(element);
//	}
	
//	public void setPlayerAtStart(Camera player) {
//		player.moveTo(start.getX(), start.getY()-15, start.getZ());
//	}
	
	

}
