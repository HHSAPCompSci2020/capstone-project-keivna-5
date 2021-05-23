package worldSetting;

import processing.core.PApplet;

public class Water extends Element{
	
	private int[] WATER_COLOR = new int[] {15, 50, 70};
	
	/**
	 * Create the Water
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size the length of the water block
	 */
	public Water(float x, float y, float z, float size) {
		super(x, y, z, size);
	}
	
	/**
	 * Draws the blue block of water
	 * @param g can't be null
	 */
	@Override
	public void display(PApplet g) {
		g.pushMatrix();
		
		g.translate(getX(), getY(), getZ());
		g.fill(WATER_COLOR[0], WATER_COLOR[1], WATER_COLOR[2]);
		g.box(getSize(), getSize() / 100, getSize());
		
		g.popMatrix();
	}
}
