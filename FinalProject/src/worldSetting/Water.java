package worldSetting;

import processing.core.PApplet;

public class Water extends Element{
	
	private int[] color;
	
	public Water(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		color = new int[] {15, 50, 70};
		
		setFillColor(color);
	}
	
	@Override
	public void display(PApplet g) {
		g.pushMatrix();
		
		g.translate(getX(), getY(), getZ());
		g.fill(getFillColor()[0], getFillColor()[1], getFillColor()[2]);
		g.box(getSize(), getSize() / 100, getSize());
		
		g.popMatrix();
	}

}
