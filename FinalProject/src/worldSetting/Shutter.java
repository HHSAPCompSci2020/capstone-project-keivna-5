package worldSetting;

import processing.core.PApplet;

public class Shutter {
	
	
	public void capture(PApplet marker) {
		//saves photos as photo-0001.png, photo-0002.png, photo-0003.png...
		marker.saveFrame("photo-####.png");
		
		marker.sketchDisplay();
	}
}
