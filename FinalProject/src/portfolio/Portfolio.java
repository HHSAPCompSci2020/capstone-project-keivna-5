	package portfolio;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import drawingPackage.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import worldSetting.Shutter;
import worldSetting.Viewfinder;

/**
 * Represents the portfolio itself
 * @author elise
 */
public class Portfolio {
	
	private ArrayList<PImage> images;
	private ArrayList<Rectangle> imageRects;
	private ArrayList<ArrayList<PImage>> longExposureImages;
	private boolean hasClickedOnImage;
	private int photoIndex;
	private Rectangle backButton;
	
	public Portfolio(int width, int height) {
		images = new ArrayList<PImage>();
		imageRects = new ArrayList<Rectangle>();
		hasClickedOnImage = false;

		longExposureImages = new ArrayList<ArrayList<PImage>>();
		photoIndex = 0;
		
		backButton = new Rectangle(width - 100, height/20, 60, height/20);
	}

	/**
	 * Draws a grid of photos from all of the photos captured in the viewfinder world
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker) {
		if (!hasClickedOnImage) {
			drawPortfolioGrid(marker);
		} else {
			drawSingleFullScreenImg(marker, photoIndex);
		}
		
	}
	
	private void drawPortfolioGrid(PApplet marker) {
		imageRects = new ArrayList<Rectangle>();
		images = Shutter.getallImages();
		longExposureImages = Shutter.getallLongExpoImages();

		marker.background(255);
		marker.text("Long Exposure Shots: ", 20, 75);

		double tint = 255.0/((double)Viewfinder.getNumPhotosPerLongExpo());
//		marker.tint(255, 10); //opacity for layering for long expo
		marker.tint(255, (int)tint); //opacity for layering for long expo

		int longExpoYMultiple = 0;
		
		int photosPerRow = 2;
		//for some reason i need to call this method bc longExposureImages.size doesn't work
		for (int a = 0; a < Shutter.longExpoSize(); a++) { //goes thru each arraylist in arrayList<arraylist<pimage>>
			ArrayList<PImage> currLongExpo = longExposureImages.get(a);
			//find out how many rows down images needs to be at
			//4 photos per row, go to next row after
			int f = 60; //viewfinder indent
			int d = a % photosPerRow; //remainder when divided by photosPerRow
			int e = a-d;
			
			longExpoYMultiple = (e/photosPerRow);

			System.out.println("Viewfinder.getNumPhotosPerLongExpo(): " + Viewfinder.getNumPhotosPerLongExpo());
			for (int b = 0; b < Viewfinder.getNumPhotosPerLongExpo(); b++) { //goes thru each pimage in the arrayList<pimage>

				PImage longExpoImg = currLongExpo.get(b);
				longExpoImg = marker.loadImage(a + "." + b + ".png");

				longExpoImg.copy(f, f, marker.width-(2*f), marker.height-(2*f), 0, 0,  marker.width, marker.height); //crop image

				marker.image(longExpoImg, 20 + (marker.width/photosPerRow*((a%photosPerRow))), ((marker.height/photosPerRow)*longExpoYMultiple) + 100, marker.width/3, marker.height/3);

//				marker.image(longExpoImg, 20 + (marker.width/photosPerRow*((a%photosPerRow))), (150*longExpoYMultiple) + 100, marker.width/3, marker.height/3);
			}
		}

		marker.tint(255); //undo from long expo

		marker.text("Simple Shots: ", 20 , (longExpoYMultiple + 2) * marker.width/5);

		//display images in row in portfolio
		for (int i = 0; i < images.size(); i++) {

			PImage img = images.get(i);
			//images name from shutter are in format "index.png"
			img = marker.loadImage(i + ".png");

			int a = 60; //viewfinder indent

			//crop image to remove viewfinder
			img.copy(a, a, marker.width-(2*a), marker.height-(2*a), 0, 0,  marker.width, marker.height);

			//find out how many rows down images needs to be at
			//4 photos per row, go to next row after
			int b = i % 4; //remainder when divided by 4
			int c = i-b; //c is divisible by 4
			int yMultiple = (c/4) + longExpoYMultiple;
			
			int x =  20 + (marker.width/4*((i%4)));
			int y = (150*yMultiple) + 350;
			int width = marker.width/5;
			int height = marker.height/5;

//			marker.image(img, 20 + (marker.width/4*((i%4))), (150*yMultiple) + 350, marker.width/5, marker.height/5);
			marker.image(img, x, y, width, height);
			//images.add(img);
			imageRects.add(new Rectangle(x, y, width, height));
		}
	}
	
	public void drawSingleFullScreenImg(PApplet marker, int index) {
		PImage img = images.get(index);
		img = marker.loadImage(index + ".png");

		int a = 60; //viewfinder indent
		img.copy(a, a, marker.width-(2*a), marker.height-(2*a), 0, 0,  marker.width, marker.height);

		marker.image(img, 0, 0, marker.width, marker.height); //image takes up entire screen
		marker.rect(backButton.x, backButton.y, backButton.width, backButton.height);
		
		marker.fill(0);
		marker.text("Back", backButton.x + 5, backButton.y + (3*backButton.height/4));
		
	}
	
	public void mousePressed(DrawingSurface marker) {		
		//start w simple shots
		Point p = new Point(marker.mouseX, marker.mouseY);
				
		//small -> large
		for (int i = 0; i < imageRects.size(); i++) {
			if (imageRects.get(i).contains(p)) {
				hasClickedOnImage = true;
				photoIndex = i;
//				marker.image(images.get(i), 0, 0, marker.width, marker.height); //image takes up entire screen

			}
			
		}
		
		if (hasClickedOnImage && backButton.contains(p)) {
			hasClickedOnImage = false;
		}
	}
}
