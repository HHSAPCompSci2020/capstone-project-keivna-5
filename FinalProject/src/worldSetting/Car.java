package worldSetting;

import processing.core.PApplet;

/**
 * Draws a Car
 * @author Katia
 *
 */
public class Car extends Element{
	
	private int[] CAR_COLOR;
	private int brightness = 100;
	
	private final int[] WHEEL_COLOR = new int[] {55, 60, 65};
	private final int[] FRONT_LIGHT_COLOR = new int[] {255, 250, 185};
	private final int[] BACK_LIGHT_COLOR = new int[] {255, 55, 0};
	
	private float carLength; //x
	private float carHeight; //y
	private float carWidth; //z

	/**
	 * Creates the base coordinates for the rest of the bridge to build off of.
	 * @param x x-coordinate for the center of the bridge
	 * @param y y-coordinate for the center of the bridge
	 * @param z z-coordinate for the center of the bridge
	 * @param size represents the width of the bridge (z-axis)
	 */
	public Car(float x, float y, float z, float size) {
		super(x, y, z, size);
		
		carLength = size * (float) (1 + Math.random());
		carHeight = size / 2;
		carWidth = size;
		
		brightness = 100;
		CAR_COLOR = new int[] {(int)(Math.random()*brightness), (int)(Math.random()*brightness), (int)(Math.random()*brightness)};
	}
	
	/**
	 * Draws the deck, two towers, suspension cords and supports
	 * @param g PApplet of the main 3D world, can't be null
	 */
	public void display(PApplet g) {	
		g.pushMatrix();
		drawBase(g);
//		drawWheel(g);
		drawLights(g);
		this.moveX(-1);
		g.popMatrix();
	}
	
	/*
	 * Draws physical elements:
	 */
	private void drawBase(PApplet g) {
		// one small base of the car
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(CAR_COLOR[0], CAR_COLOR[1], CAR_COLOR[2]);
		g.box(carLength, carHeight, carWidth);
		g.popMatrix();	
	}
	
	private void drawLights(PApplet g) {
		// one small base of the car
		for(int i = 0; i < 4; i++) {
			int side;
			int[] color;
			if(i < 2) {
				side = -1;
				color = FRONT_LIGHT_COLOR;
			} else {
				side = 1;
				color = BACK_LIGHT_COLOR;
			}
			g.pushMatrix();
			g.translate(getX() + (side * carLength/2), getY(), 
					getZ() + ((i % 2 == 0 ? -2 : 2) * carWidth / 5));
			g.noStroke();
			g.fill(color[0], color[1], color[2]);
			g.box(carLength / 8, carHeight/4, carWidth / 5);
			g.popMatrix();	
		}	
	}
	
	private void drawWheel(PApplet g) {
		// one small base of the car
		g.pushMatrix();
		g.translate(getX(), getY(), getZ());
		g.noStroke();
		g.fill(WHEEL_COLOR[0], WHEEL_COLOR[1], WHEEL_COLOR[2]);
		drawCylinder(g, 360, carLength / 6, carWidth/4);
		g.popMatrix();	
	}
	
	//https://vormplus.be/full-articles/drawing-a-cylinder-with-processing
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
