package portfolio;

import java.util.ArrayList;

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
		ArrayList<ArrayList<PImage>> longExposureImages = Shutter.getallLongExpoImages();

		marker.background(255);
		
		marker.tint(255, 126); //opacity for layering
		
		//for some reason i need to call this method bc longExposureImages.size doesn't work
		for (int a = 0; a < Shutter.longExpoSize(); a++) { //goes thru each arraylist in arrayList<arraylist<pimage>>
			ArrayList<PImage> currLongExpo = longExposureImages.get(a);
			//find out how many rows down images needs to be at
			//4 photos per row, go to next row after
			int f = 60; //viewfinder indent

			int d = a % 4; //remainder when divided by 4
			int e = a-d; //c is divisible by 4
			int yMultiple = (e/4);
			
			System.out.println("currLongExpo.size(): " + currLongExpo.size());

			for (int b = 0; b < currLongExpo.size(); b++) { //goes thru each pimage in the arrayList<pimage>
				PImage longExpoImg = currLongExpo.get(b);
				
				longExpoImg.copy(f, f, marker.width-(2*f), marker.height-(2*f), 0, 0,  marker.width, marker.height);

				longExpoImg = marker.loadImage(a + "." + b + ".png");
				marker.image(longExpoImg, 20 + (marker.width/4*((a%4))), (150*yMultiple) + 50, marker.width/5, marker.height/5);

			}
		}

		marker.tint(255); //undo from long expo


		//display images in row in portfolio, commented for now to work on long expo
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
