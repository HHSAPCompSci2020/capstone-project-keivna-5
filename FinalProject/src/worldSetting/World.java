package worldSetting;

import drawingPackage.Constants;
import processing.core.*;

public class World { //build water, bridge, background

	private Water water;
	private Bridge bridge;

	private int[] backgroundColor;

	int savedTime;
	int totalTime = 60000;

	public World(PApplet marker) {
		water = new Water(350, 350, 50, 5000);
		bridge = new Bridge(350, -500, 50, 200);

		backgroundColor = new int[] {185, 230, 255};

		savedTime = marker.millis();
		SoundPlayer.playSeaSound();

	}


	public void display(PApplet g) {
		g.background(backgroundColor[0], backgroundColor[1], backgroundColor[2]);

		water.display(g);
		bridge.display(g);

		//DO NOT DELETE!!! IDC HOW DESPERATELY YOU WANT TO DONT.
//		// Calculate how much time has passed
//		int passedTime = g.millis() - savedTime;
//		//the time is a factor of the duration of sea sound, play sound again
//		//currenlty doesn't work but it's fine bc sound plays for 4 minutes when window is opened
//		if (passedTime % Constants.seaSoundDurationMillis == 0) {
//			SoundPlayer.playSeaSound();
//
//			//System.out.println("1 minute has passed!");
//			//background(random(255)); // Color a new background
//			savedTime = g.millis(); // Save the current time to restart the timer!
//		}
	}

	public void setPlayerAtStart(Camera player) {
		//		player.moveTo(element.getX(), element.getY()-15, element.getZ());
	}

	public void setBackground(int[] color) {
		backgroundColor = color;
	}

}
