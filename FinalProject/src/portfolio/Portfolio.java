package portfolio;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Portfolio {
	ArrayList<PImage> images;
	PImage img;
	PGraphics pg;
	
	public Portfolio() {
		images = new ArrayList<PImage>();
	}
	
	public void setup(PApplet marker) {
//		img = marker.loadImage("photo-0138.png");
		//pg = createGraphics(80, 80, processing.core.P2D);
//		img = marker.loadImage("photo1.png");

	}
	
	public void draw(PApplet marker) {
		img = marker.loadImage("photo-0138.png");

		marker.background(185, 230, 255);
		//System.out.println("img: " + img.toString());
		marker.image(img, 0, 0, marker.width/5, marker.height/5);
		
		//bridge.display(marker);
		//water.display(marker);
	}
}
