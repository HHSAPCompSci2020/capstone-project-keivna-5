package worldSetting;

import processing.core.PApplet;

public class Bridge extends Element{
	
	private int numCords;
	
	private final int[] METAL_COLOR = new int[] {160, 50, 50};
	private final int[] ROAD_COLOR = new int[] {110, 120, 120};

	// the point that gets passed in should be starting point of the base of the bridge
	public Bridge(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		numCords = 20;
	}
	
	public void display(PApplet g) {	
		// draws the base
		g.pushMatrix();
		drawDeck(g);
		// draws 2 towers
		drawTower(g);
		drawTower(g);
		// draws the suspension cords
		for (int i = 0; i < numCords; i++) {
			drawSuspensionCord(g);
		}
			// which draws the supports
		
		g.popMatrix();
	}
	
	//draw physical components:
	
	private void drawDeck(PApplet g) {
		// one Rectangular red prism
		int x0 = (int) getX();
		int y0 = (int) getY();
		int z0 = (int) getZ();
		
		g.pushMatrix();
		g.translate(x0, y0, z0);
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.box(getSize() * 10, getSize() / 5, getSize());
		g.popMatrix();
		
		// one grey rect prism
		g.pushMatrix();
		g.translate(x0, y0 - (getSize() / 10), z0);
		g.fill(ROAD_COLOR[0], ROAD_COLOR[1], ROAD_COLOR[2]);
		g.box(getSize() * 10, getSize() / 5, getSize());
		g.popMatrix();
		// one red rect prism
		//one red rect prism underneath grey prism
	}
	
	private void drawTower(PApplet g) {
		// stack of n shaped objects
		// decreasing in height
		// each n made of
		// vertical rectangular prism
		// horizontal rectangular prism
		// same size v rect prism, just at the end of h prism
	}
	
	private void drawSuspensionCord(PApplet g) {
		// starting point should be top of one tower
		// mid part of the arc be mid point (p much touching the base
		// ending point at the top of the second tower
	}
	
	private void drawSupports(PApplet g) {
		// evenly spaced apart
		// is a simple vertical cylindrical going from the suspension to the deck
	}

}
