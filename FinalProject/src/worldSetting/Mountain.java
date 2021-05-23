package worldSetting;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

/**
 * Draws a Mountain
 * @author Katia
 *
 */
public class Mountain extends Element{
	
	private final int[][] MOUNTAIN_COLORS = new int[][] {
		{50, 65, 40}, //dark green
		{45, 30, 10}, //dark brown
		{90, 80, 40}  //dark yellow
	};

	//honestly won't use these
	private float mountainLength; //x
	private float mountainHeight; //y
	private float mountainWidth; //z
	
	private PImage mountainColor;

	/**
	 * Creates the base coordinates for the rest of the mountain to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Mountain(float x, float y, float z, float size) { // int numOfRidges
		super(x, y, z, size);
		
		mountainLength = 3 * size / 2;
		mountainHeight = size;
		mountainWidth = size;
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
//		mountainColor = g.loadImage("media/mountain-color.jpg");
		drawMountain(g);
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawMountain(PApplet g) {
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
//		g.texture(mountainColor);
		g.noStroke();
//		g.fill(MOUNTAIN_COLORS[i][0], MOUNTAIN_COLORS[i][1], MOUNTAIN_COLORS[i][2]);		
		g.beginShape(PApplet.TRIANGLE_STRIP);
	    for (int i = 0; i < 3; i++) {
			g.fill(MOUNTAIN_COLORS[i][0], MOUNTAIN_COLORS[i][1], MOUNTAIN_COLORS[i][2]);
	        g.vertex(getX(), getY() - (10 * i), getZ() + (5 * i) );    
	    }
	    g.endShape(PApplet.CLOSE);
		
		g.popMatrix();	
	}

}
