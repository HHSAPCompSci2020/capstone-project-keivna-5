package worldSetting;

import processing.core.*;

public class Element {
	private float x, y, z, size;
	private int[] fillColor;
	
	//for now just draws a block
	public Element(float x, float y, float z, float size, int[] fillColor) {
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
		fillColor = new int[] {200, 140, 230};
	}

	public void display(PApplet g) {
		g.pushMatrix();
		g.translate(x, y, z);
		g.fill(fillColor[0], fillColor[1], fillColor[2]);
		g.box(size, size, size);
		g.popMatrix();
	}

	public float getX() { return x; }

	public float getY() { return y; }

	public float getZ() { return z; }

	public float getSize() { return size; }
	
	public int[] getFillColor() { return fillColor; }
	
	public void setFillColor(int[] newColor) { fillColor = newColor;}
	
}
