package worldSetting;

import mazeexample.Player;
import processing.core.PApplet;

public class World { //build water, bridge
	
	private Bridge bridge;
	private Water water;
	private PApplet marker;
	private Element element;
	
	public World(PApplet marker) {
		element = new Element(0, 0, 0, 4);

		element = new Element(5, 0, 3, 4);
		//bridge = new Bridge(5, 5, 5, 10);
		//water = new Water(12, 12, 12, 8);
		this.marker = marker;
	}
	
	public void display() {
		//bridge.display(marker);
		//water.display(marker);
		element.display(marker);
	}
	
//	public void update(Camera p) {
//		p.act(element);
//	}
	
//	public void setPlayerAtStart(Camera player) {
//		player.moveTo(start.getX(), start.getY()-15, start.getZ());
//	}
	
	

}
