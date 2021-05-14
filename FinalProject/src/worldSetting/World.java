package worldSetting;

import java.util.ArrayList;
import processing.core.*;

public class World { //build water, bridge, background
	
	private Water water;
	private Bridge bridge;
	
	private int[] backgroundColor;
	
	public World() {
		water = new Water(350, 350, 50, 5000);
		bridge = new Bridge(350, -500, 50, 200);
		
		backgroundColor = new int[] {185, 230, 255};
	}

	public void display(PApplet g) {
		g.background(backgroundColor[0], backgroundColor[1], backgroundColor[2]);
		
		water.display(g);
		bridge.display(g);
		SoundPlayer.playSeaSound();
	}
	
	public void setPlayerAtStart(Camera player) {
//		player.moveTo(element.getX(), element.getY()-15, element.getZ());
	}
	
	public void setBackground(int[] color) {
		backgroundColor = color;
	}

}
