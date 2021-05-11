package drawingPackage;
import java.awt.Point;

import processing.core.PApplet;

public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	public void setup() {
		
	}
	
	public void draw(PApplet drawer) {
		
	}
	
	public void mousePressed(DrawingSurface surface) {
		System.out.println("mouse pressed from abstract class");
	}
	
	public void mouseMoved() {
		
	}
	
	public void mouseDragged() {
		
	}
	
	public void mouseReleased() {
		
	}
	
	public void keyPressed(PApplet marker) {
		
	}
	
	public void keyReleased(PApplet marker) {
		
	}


	
}
