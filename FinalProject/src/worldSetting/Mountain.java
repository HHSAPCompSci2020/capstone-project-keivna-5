package worldSetting;

import processing.core.PApplet;

/**
 * Draws a Mountain
 * @author Katia
 *
 */
public class Mountain extends Element{
	
	// TODO: try using these to make gradient
	private final int[][] MOUNTAIN_COLORS = new int[][] {
		{50, 65, 40}, //dark green
		{45, 30, 10}, //dark brown
		{90, 80, 40}  //dark yellow
	};

	private float height;
	private float sideLength;

	/**
	 * Creates the base coordinates for the rest of the mountain to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Mountain(float x, float y, float z, float size) { // int numOfRidges
		super(x, y, z, size);
		
		height = size;
		sideLength = size / 2;
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(MOUNTAIN_COLORS[0][0], MOUNTAIN_COLORS[0][1], MOUNTAIN_COLORS[0][2]);
		g.rotateX(g.PI/2);
		drawMountain(g);
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawMountain(PApplet g) {
		g.pushMatrix();
		g.beginShape(PApplet.TRIANGLES);

		g.vertex(getX() + sideLength, getY() - sideLength, getZ()); 
		g.vertex(getX() - sideLength, getY() - sideLength, getZ());
		g.vertex(getX(), getY(), getZ() + height);

		g.vertex(getX() - sideLength, getY() - sideLength, getZ());
		g.vertex(getX() - sideLength, getY() + sideLength, getZ());
		g.vertex(getX(), getY(), getZ() + height);

		g.vertex(getX() - sideLength, getY() + sideLength, getZ());
        g.vertex(getX() + sideLength, getY() + sideLength, getZ());
        g.vertex(getX(), getY(), getZ() + height);

        g.vertex(getX() + sideLength, getY() + sideLength, getZ());
        g.vertex(getX() + sideLength, getY() - sideLength, getZ()); 
        g.vertex(getX(), getY(), getZ() + height);

        g.endShape(PApplet.CLOSE);
		g.popMatrix();	
	}

}
