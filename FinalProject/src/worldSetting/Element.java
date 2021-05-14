package worldSetting;

import processing.core.*;

/**
 * represents a simple 3D element
 * @author katia
 *
 */
public class Element {
	private float x, y, z, size;
	private int[] fillColor;
	
	/**
	 * initializes element fields
	 * @param x
	 * @param y
	 * @param z
	 * @param size
	 * @param fillColor
	 */
	public Element(float x, float y, float z, float size, int[] fillColor) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		this.fillColor = fillColor;
	}
	
	/**
	 * initializes fields
	 * @param x
	 * @param y
	 * @param z
	 * @param size
	 */
	public Element(float x, float y, float z, float size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		fillColor = new int[] {200, 140, 230};
	}

	/**
	 * draws a simple box
	 * @param g
	 * @pre PApplet g is not null
	 * @post PApplet g is modified
	 */
	public void display(PApplet g) {
		g.pushMatrix();
		g.translate(x, y, z);
		g.fill(fillColor[0], fillColor[1], fillColor[2]);
		g.box(size, size, size);
		g.popMatrix();
	}

	/**
	 * @return X
	 */
	public float getX() { return x; }

	/**
	 * @return Y
	 */
	public float getY() { return y; }

	/**
	 * @return Z
	 */
	public float getZ() { return z; }

	/**
	 * @return size
	 */
	public float getSize() { return size; }
	
	/**
	 * @return fillColor
	 */
	public int[] getFillColor() { return fillColor; }
	
	/**
	 * sets fillColor to param
	 * @param newColor
	 */
	public void setFillColor(int[] newColor) { fillColor = newColor;}	
}
