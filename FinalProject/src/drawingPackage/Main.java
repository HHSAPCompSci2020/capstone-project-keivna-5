package drawingPackage;

import processing.core.*;

public class Main{

	/**
	 * Creates the DrawingSurface for the application
	 * @param args whatever needs to be passed in
	 */
	public static void main(String[] args) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
	}
}
