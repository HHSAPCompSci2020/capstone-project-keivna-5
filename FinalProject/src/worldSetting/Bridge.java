package worldSetting;

import processing.core.PApplet;

public class Bridge extends Element{
	
	private final int[] METAL_COLOR = new int[] {160, 50, 50};
	private final int[] ROAD_COLOR = new int[] {110, 120, 120};
	
	private int numCords;
	private float bridgeLength;
	private float bridgeHeight;

	// the point that gets passed in should be starting point of the base of the bridge
	// size represents the width of the bridge
	public Bridge(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		numCords = 20;
		bridgeLength = size * 10;
		bridgeHeight = size / 5;
	}
	
	public void display(PApplet g) {	
		g.pushMatrix();
		
		drawDeck(g);
		drawTower(g);
		drawTower(g);
		for (int i = 0; i < numCords; i++) {
			drawSuspensionCord(g);
			// which draws the supports
		}
		
		g.popMatrix();
	}
	
	//draw physical components:
	
	private void drawDeck(PApplet g) {
		float sideHeight = bridgeHeight / 2;
		
		//one red rect prism underneath grey prism
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.box(bridgeLength, bridgeHeight, getSize());
		g.popMatrix();
		
		// one grey rect prism
		g.pushMatrix();
		g.translate(getX(), getY() - (sideHeight), getZ());
		g.fill(ROAD_COLOR[0], ROAD_COLOR[1], ROAD_COLOR[2]);
		g.box(bridgeLength, bridgeHeight, getSize());
		g.popMatrix();
		
		// two red rect prisms to the left and right
		g.pushMatrix();
		g.translate(getX(), getY() - sideHeight, getZ() - (int) (bridgeHeight * 2.5));
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.box(bridgeLength, bridgeHeight * 2, sideHeight);
		g.popMatrix();
		
		g.pushMatrix();
		g.translate(getX(), getY() - sideHeight, getZ() + (int) (bridgeHeight * 2.5));
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.box(bridgeLength, bridgeHeight * 2, sideHeight);
		g.popMatrix();
		
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
