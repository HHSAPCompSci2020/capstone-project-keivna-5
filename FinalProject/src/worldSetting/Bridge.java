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
	
	private int towerHeightInNs;
	private int towerNHeight;
	private int numCords;

	/**
	 * Creates the base coordinates for the rest of the bridge to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Bridge(float x, float y, float z, float size, int towerHeight) {
		super(x, y, z, size);
		
		numCords = 20;
		bridgeLength = size * 10;
		bridgeHeight = size / 5;
		bridgeWidth = size;
		towerHeightInNs = towerHeight;
		towerNHeight = 100;
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		drawDeck(g);
		drawTower(g, getX() + (bridgeLength / 5));
		drawTower(g, getX() - (bridgeLength / 5));
		for (int i = 0; i < numCords; i++) {
			drawSuspensionCord(g); // which draws the supports
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
		// stack of n shaped objects, decreasing in height, each n made of,
		// vertical rectangular prism, horizontal rectangular prism
		// same size v rect prism, just at the end of h prism
		g.fill(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		for(int i = 0; i < towerHeightInNs; i++) {
			g.pushMatrix();
			g.translate(x, getY() - (towerNHeight * i) + (bridgeHeight/2), getZ() + (int) (bridgeHeight * 2.5));
			g.box(bridgeHeight, towerNHeight * i, (bridgeHeight / 2) - i);
			g.popMatrix();
			
			g.pushMatrix();
			g.translate(x, getY() - (towerNHeight * i) - (bridgeHeight * i), getZ());
			g.box(bridgeHeight, bridgeHeight - i, bridgeWidth);
			g.popMatrix();
			
			g.pushMatrix();
			g.translate(x, getY() - (towerNHeight * i) + (bridgeHeight/2), getZ() - (int) (bridgeHeight * 2.5));
			g.box(bridgeHeight, towerNHeight * i, (bridgeHeight / 2) - i);
			g.popMatrix();
		}
	}
	
	private void drawSuspensionCord(PApplet g) {
		float radiusOfArc = (bridgeLength / 5);
		
		g.strokeWeight(10);
		g.stroke(METAL_COLOR[0], METAL_COLOR[1], METAL_COLOR[2]);
		g.noFill();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() - (int) (bridgeHeight * 2.5));
		g.arc(getX() + radiusOfArc + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, g.HALF_PI, g.PI);
		g.popMatrix();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() - (int) (bridgeHeight * 2.5));
		g.arc(getX() - radiusOfArc + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, 0, g.PI);
		g.popMatrix();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() - (int) (bridgeHeight * 2.5));
		g.arc(getX() - (3 * radiusOfArc) + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, 0, g.HALF_PI);
		g.popMatrix();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() + (int) (bridgeHeight * 2.5));
		g.arc(getX() + radiusOfArc + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, g.HALF_PI, g.PI);
		g.popMatrix();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() + (int) (bridgeHeight * 2.5));
		g.arc(getX() - radiusOfArc + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, 0, g.PI);
		g.popMatrix();
		
		g.pushMatrix(); // start: top of tower 1, mid: point between towers, end: top of tower 2
		g.translate(getX(), getY(), getZ() + (int) (bridgeHeight * 2.5));
		g.arc(getX() - (3 * radiusOfArc) + bridgeHeight, getY() - (3 * bridgeHeight), radiusOfArc * 2, radiusOfArc * 1.5f, 0, g.HALF_PI);
		g.popMatrix();
	}
	
	private void drawSupports(PApplet g) {
		// evenly spaced apart, simple vertical cylindrical going from the suspension to the deck
	}
}
