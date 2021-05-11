package worldSetting;

import java.awt.Color;

import processing.core.*;

public class Element {
	private float x, y, z, size;
	private Color fillColor;
	
	//for now just draws a block
	public Element(float x, float y, float z, float size, Color fillColor) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		this.fillColor = fillColor;
	}
	
	public Element(float x, float y, float z, float size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
		fillColor = Color.pink;
	}

	public void display(PApplet g) {
		g.pushMatrix();
		g.translate(x, y, z);
//		g.fill(fillColor);
		g.box(size, size, size);
		g.popMatrix();
	}

//	might need, who knows
//	public boolean isPointInCube(float x, float y, float z) {
//		// the x y z coords of the block are in the center so +/- by size/2 in all
//		// directions to get the edges
//		float left = this.x - size / 2;
//		float right = this.x + size / 2;
//		float top = this.y - size / 2;
//		float bottom = this.y + size / 2;
//		float front = this.z - size / 2;
//		float back = this.z + size / 2;
//		if (x > left && x < right && y > top && y < bottom && z > front && z < back) {
//			return true;
//		}
//
//		return false;
//	}

	public float getX() { return x; }

	public float getY() { return y; }

	public float getZ() { return z; }

	public float getSize() { return size; }
	
	public int getFillColor() { return fillColor; }
	
}
