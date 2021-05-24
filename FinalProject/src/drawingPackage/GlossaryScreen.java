package drawingPackage;

import java.awt.Point;

import java.awt.Rectangle;

import portfolio.Portfolio;
import processing.core.PApplet;

/**
 * The Screen that draws the Glossary
 * @author Elise
 */
public class GlossaryScreen extends Screen {

	private static final double screenWidth = 800, screenHeight = 600;
	
	/**
	 * GlossaryScreen constructor
	 * Initializes all fields
	 */
	public GlossaryScreen() {
		super((int)screenWidth, (int)screenHeight);
	}
	
	/**
	 * Draws a glossary describing functions on viewfinder
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {				
		marker.pushStyle();
		
		marker.background(168, 212, 247); //light blue
		marker.fill(0);

		marker.text("ISO: ", (int)(screenWidth/20), (int)(screenHeight/10));
		String ISOabout = "- brighten or darken photos\n"
				+ "- high ISO results in high noise (grain)\n"
				+ "- always try to use the lowest setting possible (native ISO, usually around 100 or 200) \n"
				+ "- only use ISO to brighten a photo if you can't use shutter speed or aperture\n"
				+ "- exponential scale, double ISO is double brightness";
		
		marker.text(ISOabout, (int)(screenWidth/19), (int)(screenHeight/8));

		marker.popStyle();			
	}
}