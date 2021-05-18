package worldSetting;

import processing.core.PApplet;

/**
 * Draws a Car
 * @author Katia
 *
 */
public class Car extends Element{
	
	private int[] CAR_COLOR;
	private final int[] WHEEL_COLOR = new int[] {55, 60, 65};
	
	private float carLength; //x
	private float carHeight; //y
	private float carWidth; //z

	/**
	 * Creates the base coordinates for the rest of the bridge to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Car(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		carLength = size * (float) (1 + Math.random());
		carHeight = size / 2;
		carWidth = size;
		
		CAR_COLOR = new int[] {(int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)};
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		drawBase(g);
		this.moveX(-2);
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawBase(PApplet g) {
		// one small base of the car
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(CAR_COLOR[0], CAR_COLOR[1], CAR_COLOR[2]);
		g.box(carLength, carHeight, carWidth);
		g.popMatrix();	
	}
	
	private void drawWheel(PApplet g) {
		// one small base of the car
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(WHEEL_COLOR[0], WHEEL_COLOR[1], WHEEL_COLOR[2]);
		g.circle(getX(), getY(), carWidth / 2);
		g.popMatrix();	
	}

}
