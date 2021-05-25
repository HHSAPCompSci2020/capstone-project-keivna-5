package worldSetting;

import processing.core.*;

/**
 * Represents a simple 3D element
 * @author Katia
 *
 */
public class Element {
	
	private float x, y, z, size;
	private int[] fillColor;
	
	/**
	 * Initializes element fields
	 * @param x x-coordinate of the Element
	 * @param y y-coordinate of the Element
	 * @param z z-coordinate of the Element
	 * @param size the length of the cube
	 * @param fillColor the color of the cube in [r,g,b]
	 */
	public Element(float x, float y, float z, float size, int[] fillColor) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		this.fillColor = fillColor;
	}
	
	/**
	 * Initializes fields
	 * @param x x-coordinate of the Element
	 * @param y y-coordinate of the Element
	 * @param z z-coordinate of the Element
	 * @param size the length of the cube
	 */
	public Element(float x, float y, float z, float size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		fillColor = new int[] {200, 140, 230};
	}

	/**
	 * Draws a simple box with the color fillColor
	 * and the length of size and coordinates of x, y, z
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
	 * draws a simple cylinder
	 * Written by https://vormplus.be/full-articles/drawing-a-cylinder-with-processing
	 * @param g
	 * @param sides
	 * @param r
	 * @param h
	 * @pre PApplet is not null
	 * @post PApplet may be modified
	 */
	public void drawCylinder(PApplet g, int sides, float r, float h){
		float angle = 360 / sides;
		float halfHeight = h / 2;
		// draw top shape
		g.beginShape();
		for (int i = 0; i < sides; i++) {
			float x = PApplet.cos( PApplet.radians( i * angle ) ) * r;
			float y = PApplet.sin( PApplet.radians( i * angle ) ) * r;
			g.vertex( x, y, -halfHeight );    
		}
		g.endShape(PApplet.CLOSE);

		// draw bottom shape
		g.beginShape();
		for (int i = 0; i < sides; i++) {
			float x = PApplet.cos( PApplet.radians( i * angle ) ) * r;
			float y = PApplet.sin( PApplet.radians( i * angle ) ) * r;
			g.vertex( x, y, halfHeight );    
		}
		g.endShape(PApplet.CLOSE);

		// draw body (connects top and bottom)
		g.beginShape(PApplet.TRIANGLE_STRIP);
		for (int i = 0; i < sides + 1; i++) {
			float x = PApplet.cos( PApplet.radians( i * angle ) ) * r;
			float y = PApplet.sin( PApplet.radians( i * angle ) ) * r;
			g.vertex( x, y, halfHeight);
			g.vertex( x, y, -halfHeight);    
		}
		g.endShape(PApplet.CLOSE);
	}

	/**
	 * Get x-coordinate of the Element
	 * @return X The current x-coordinate
	 */
	public float getX() { return x; }
	
	/**
	 * @param newX to set the x to
	 */
	public void setX(float newX) { x = newX;}
	
	/**
	 * Moves x-coordinate of the Element
	 * @param add Move x by this amount
	 */
	public void moveX(float add) { x += add;}	

	/**
	 * Get y-coordinate of the Element
	 * @return Y The current y-coordinate
	 */
	public float getY() { return y; }
	
	/**
	 * @param newY to set the y to
	 */
	public void setY(float newY) { y = newY; }
	
	/**
	 * Moves y-coordinate of the Element
	 * @param add Move y by this amount
	 */
	public void moveY(float newY) { y = newY; } 

	/**
	 * Get z-coordinate of the Element
	 * @return Z The current z-coordinate
	 */
	public float getZ() { return z; }

	/**
	 * Get the length of the Element
	 * @return size The current size
	 */
	public float getSize() { return size; }
	
	/**
	 * Get the color of the Element
	 * @return fillColor The current color
	 */
	public int[] getFillColor() { return fillColor; }
	
	/**
	 * Sets fillColor to a new color
	 * @param newColor New color
	 */
	public void setFillColor(int[] newColor) { fillColor = newColor;}	
}
