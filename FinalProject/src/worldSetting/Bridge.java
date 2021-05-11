package worldSetting;

import processing.core.PApplet;

public class Bridge extends Element{

	public Bridge(float x, float y, float z, float size) {
		super(x, y, z, size);
	}
	
	public void display(PApplet g) {	
		// draws the base
		// draws 2 towers
		
	}
	
	//draw physical components:
	
	private void drawDeck(PApplet g) {
		// one Rectangular red prism
		// one grey rect prism
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
