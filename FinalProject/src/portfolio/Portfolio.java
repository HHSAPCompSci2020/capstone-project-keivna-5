package portfolio;

import java.util.ArrayList;

import drawingPackage.Constants;
import processing.core.PApplet;
import processing.core.PImage;
import worldSetting.Shutter;

/**
 * Represents the portfolio itself
 * @author elise
 */
public class Portfolio {

	/**
	 * Draws a grid of photos from all of the photos captured in the viewfinder world
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {
		ArrayList<PImage> images = Shutter.getallImages();
		marker.background(255);

		//display images in row in portfolio
		for (int i = 0; i < images.size(); i++) {

			PImage img = images.get(i);
			//images name from shutter are in format "index.png"
			img = marker.loadImage(i + ".png");

			//crop image to remove viewfinder
			int a = 60; //viewfinder indent
			img.copy(a, a, marker.width-(2*a), marker.height-(2*a), 0, 0,  marker.width, marker.height);


			//find out how many rows down images needs to be at
			//4 photos per row, go to next row after
			int b = i % 4; //remainder when divided by 4
			int c = i-b; //c is divisible by 4
			int yMultiple = (c/4);

			//marker.image(img, 20 + (marker.width/4*i), y, marker.width/5, marker.height/5);
			marker.image(img, 20 + (marker.width/4*((i%4))), (150*yMultiple) + 50, marker.width/5, marker.height/5);
		}
	}
}
