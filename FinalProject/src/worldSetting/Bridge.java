package worldSetting;

import processing.core.PApplet;

/**
 * Draws the Golden Gate Bridge
 * @author Katia
 *
 */
public class Bridge extends Element{
	
	private final int[] METAL_COLOR = new int[] {160, 50, 50};
	private final int[] ROAD_COLOR = new int[] {110, 120, 120};
	
	private float bridgeLength; //x
	private float bridgeHeight; //y
	private float bridgeWidth; //z
	
	private int towerHeightInNs = 4;
	private int numCords;
	private int towerHeight;

	/**
	 * Creates the base coordinates for the rest of the bridge to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Bridge(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		numCords = 20;
		bridgeLength = size * 10;
		bridgeHeight = size / 5;
		bridgeWidth = size;
		towerHeight = 0;
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		drawDeck(g);
		drawTower(g, getX() + (bridgeLength / 6));
		drawTower(g, getX() - (bridgeLength / 6));
		for (int i = 0; i < numCords; i++) {
			drawSuspensionCord(g);
			// which draws the supports
		}
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawDeck(PApplet g) {
		//one red rect prism underneath grey prism
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.box(bridgeLength, bridgeHeight, bridgeWidth);
		g.popMatrix();
		
		float sideHeight = bridgeHeight / 2;
		
		// one grey rect prism
		g.pushMatrix();
		g.translate(getX(), getY() - (sideHeight), getZ());
		g.fill(ROAD_COLOR[0], ROAD_COLOR[1], ROAD_COLOR[2]);
		g.box(bridgeLength, bridgeHeight, bridgeWidth);
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
	
	private void drawTower(PApplet g, float x) {
		// stack of n shaped objects, decreasing in height, each n made of, vertical rectangular prism, horizontal rectangular prism
		// same size v rect prism, just at the end of h prism
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		int levelHeight = 100;
		for(int i = towerHeightInNs; i > 0; i--) {
			towerHeight += levelHeight * i;
			
			g.pushMatrix();
			g.translate(x, getY() - (levelHeight * i) - ((bridgeHeight/2) * i), getZ() + (int) (bridgeHeight * 2.5));
			g.box(bridgeHeight, levelHeight * i, (bridgeHeight / 2) - i);
			g.popMatrix();
			
			g.pushMatrix();
			g.translate(x, getY() - ((i == 0? 1: i) * levelHeight) - ((i == 0? 1: i) * bridgeHeight), getZ());
			g.box(bridgeHeight, bridgeHeight - (i == 0? 1: i), bridgeWidth);
			g.popMatrix();
			
			g.pushMatrix();
			g.translate(x, getY() - (levelHeight * i) - ((bridgeHeight/2) * i), getZ() - (int) (bridgeHeight * 2.5));
			g.box(bridgeHeight, levelHeight * i, (bridgeHeight / 2) - i);
			g.popMatrix();
		}
	}
	
	private void drawSuspensionCord(PApplet g) {
		// starting point should be top of one tower
		g.strokeWeight(10);
		g.noFill();
//		g.arc(getX(), getY() - (bridgeHeight * 2), 100, 100, g.PI + g.HALF_PI, g.PI * 2f, g.OPEN); //g.OPEN);  g.QUARTER_PI
		g.arc(getX(), getY() - (bridgeHeight * 5), 80, 80, 3 * g.PI / 4, g.PI + g.QUARTER_PI, g.OPEN);
		// mid part of the arc be mid point (p much touching the base
		// ending point at the top of the second tower
	}
	
	private void drawSupports(PApplet g) {
		// evenly spaced apart
		// is a simple vertical cylindrical going from the suspension to the deck
	}

}
