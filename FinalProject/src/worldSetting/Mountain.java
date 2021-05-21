package worldSetting;

import processing.core.PApplet;

/**
 * Draws a Mountain
 * @author Katia
 *
 */
public class Mountain extends Element{
	
	private final int[] MOUNTAIN_COLOR = new int[] {55, 60, 65};

	
	private float mountainLength; //x
	private float mountainHeight; //y
	private float mountainWidth; //z
	
//	private int unevenness;

	/**
	 * Creates the base coordinates for the rest of the mountain to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Mountain(float x, float y, float z, float size) { // int numOfRidges
		super(x, y, z, size);
		
		mountainLength = size * (float) (1 + Math.random());
		mountainHeight = size / 2;
		mountainWidth = size;
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		drawMountain(g);
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawMountain(PApplet g) {
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(MOUNTAIN_COLOR[0], MOUNTAIN_COLOR[1], MOUNTAIN_COLOR[2]);
		// gonna have a lot of random vertexes, and connect them using a line
		
		g.popMatrix();	
	}
	
	
	//Written by https://vormplus.be/full-articles/drawing-a-cylinder-with-processing
	private void drawCylinder(PApplet g, int sides, float r, float h){
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
	    
	    // draw body
	    g.beginShape(PApplet.TRIANGLE_STRIP);
	    for (int i = 0; i < sides + 1; i++) {
	        float x = PApplet.cos( PApplet.radians( i * angle ) ) * r;
	        float y = PApplet.sin( PApplet.radians( i * angle ) ) * r;
	        g.vertex( x, y, halfHeight);
	        g.vertex( x, y, -halfHeight);    
	    }
	    g.endShape(PApplet.CLOSE);
	}

}
