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
	
//	private ArrayList<ArrayList<PImage>> longExposureImages;
	private ArrayList<LongExpoPhoto> longExposureImages;

	private ArrayList<Rectangle> longExpoImageRects;

	private boolean hasClickedOnImage;
	private boolean isLongExpo;

	private int photoIndex;
	private Rectangle backButton;
	
	private final int viewfinderIndent = 60;
	
	/**
	 * initializes all fields in portfolio
	 * @param width of screen
	 * @param height of screen
	 */
	public Portfolio(int width, int height) {
		images = new ArrayList<PImage>();
		imageRects = new ArrayList<Rectangle>();
		hasClickedOnImage = false;
		isLongExpo = false;
		
//		longExposureImages = new ArrayList<ArrayList<PImage>>();
		longExposureImages = new ArrayList<LongExpoPhoto>();

		longExpoImageRects = new ArrayList<Rectangle>();
		photoIndex = 0;
		
		backButton = new Rectangle(width - 100, height/20, 60, height/20);
	}

	/**
	 * Draws a grid of photos from all of the photos captured in the viewfinder world
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker, Viewfinder viewfinder) {
		if (!hasClickedOnImage) {
			drawPortfolioGrid(marker, viewfinder);
		} else {
			drawSingleFullScreenImg(marker, photoIndex, isLongExpo);
		}
	}
	
	private void drawPortfolioGrid(PApplet marker, Viewfinder viewfinder) {
		imageRects = new ArrayList<Rectangle>();
		longExpoImageRects = new ArrayList<Rectangle>();

//		images = Shutter.getallImages();
//		longExposureImages = Shutter.getallLongExpoImages();

//		ArrayList<PImage> imagesInsideDraw = new ArrayList<PImage>();
//		imagesInsideDraw = viewfinder.getAllSingleShotPhotos();
//		longExposureImages = viewfinder.getallLongExpoImages();
		images = viewfinder.getAllSingleShotPhotos();
		longExposureImages = viewfinder.getAllLongExpoImages();
		
		marker.background(255);
		marker.text("Click on a photo to expand it!", 20, 55);
		marker.text("Long Exposure Shots: ", 20, 75);

		int longExpoYMultiple = 0;
		
		int photosPerRow = 4;
		//for some reason i need to call this method bc longExposureImages.size doesn't work
		for (int a = 0; a < longExposureImages.size(); a++) { //goes thru each arraylist in arrayList<arraylist<pimage>>
			//find out how many rows down images needs to be at
			int d = a % photosPerRow; //remainder when divided by photosPerRow
			int e = a-d;
			int b = a % 4; //remainder when divided by 4

			int c = a-b; //c is divisible by 4

			longExpoYMultiple = (e/photosPerRow);
			int yMultiple = (c/4);

//			int x = 20 + (marker.width/photosPerRow*((a%photosPerRow)));
			int x =  20 + (marker.width/4*((a%4)));

//			int y = ((marker.height/photosPerRow)*longExpoYMultiple) + 100;
			int y = (150*yMultiple) + 100;

			int width = marker.width/5;
			int height = marker.height/5;
			
			drawSingleLongExpo(marker, a, x, y, width, height);
			longExpoImageRects.add(new Rectangle(x, y, width, height));			
		}

		marker.tint(255); //undo from long expo
		marker.text("Simple Shots: ", 20 , (longExpoYMultiple + 2) * marker.width/5);

		//display images in row in portfolio
		for (int i = 0; i < images.size(); i++) {
			//find out how many rows down images needs to be at
			//4 photos per row, go to next row after
			int b = i % 4; //remainder when divided by 4
			int c = i-b; //c is divisible by 4
			int yMultiple = (c/4) + longExpoYMultiple;
			
			int x =  20 + (marker.width/4*((i%4)));
			int y = (150*yMultiple) + 350;
			int width = marker.width/5;
			int height = marker.height/5;
			
			drawSingleShot(marker, i, x, y, width, height);
			imageRects.add(new Rectangle(x, y, width, height));
		}
	}
	
	private void drawSingleFullScreenImg(PApplet marker, int index, boolean isLongExpo) {
		marker.background(255);
		if (isLongExpo) {
			drawSingleLongExpo(marker, index, 0, 0, marker.width, marker.height);
		} else {
			drawSingleShot(marker, index, 0, 0, marker.width, marker.height);
		}
		
		//back button
		marker.rect(backButton.x, backButton.y, backButton.width, backButton.height);
		marker.fill(255);
		marker.text("Back", backButton.x + 5, backButton.y + (3*backButton.height/4));
	}
	
	private void drawSingleShot(PApplet marker, int index, int x, int y, int width, int height) {
		PImage img = images.get(index);
		img = marker.loadImage(index + ".png");
		img.copy(viewfinderIndent, viewfinderIndent, marker.width-(2*viewfinderIndent), marker.height-(2*viewfinderIndent), 0, 0,  marker.width, marker.height);
		marker.image(img, x, y, width, height); //image takes up entire screen
	}
	
	private void drawSingleLongExpo(PApplet marker, int index, int x, int y, int width, int height) {
		System.out.println("make 20 added a variable of 3, 5, 10 etc!");
		//60 photos needs 10, divide by 6
//		double tint = 255.0/((double)Viewfinder.getNumPhotosPerLongExpo()) + (Viewfinder.getNumPhotosPerLongExpo()/2);
		LongExpoPhoto longExpo = longExposureImages.get(index);
		
		double tint = 255.0/((double)longExpo.numPhotos());

		marker.tint(255, (int)tint); //opacity for layering for long expo
		
		
		//marker.ambientLight(255, 255, 255);

		for (int b = 0; b < longExpo.numPhotos(); b++) { //goes thru each pimage in the arrayList<pimage>
			PImage longExpoImg = longExpo.getPhoto(b);
			longExpoImg = marker.loadImage(index + "." + b + ".png");
			longExpoImg.copy(viewfinderIndent, viewfinderIndent, marker.width-(2*viewfinderIndent), marker.height-(2*viewfinderIndent), 0, 0,  marker.width, marker.height); //crop image
			longExpoImg.filter(marker.ERODE);
			marker.image(longExpoImg, x, y, width, height);
		}
		marker.tint(255);
	}
	
	/**
	 * mouse pressed method for portfolio to detect if an image needs to be enlargened or go back to the grid view
	 * @param marker
	 * @pre marker is not null
	 */
	public void mousePressed(DrawingSurface marker) {		
		Point p = new Point(marker.mouseX, marker.mouseY);
				
		//small -> large for single shots
		for (int i = 0; i < imageRects.size(); i++) {
			if (imageRects.get(i).contains(p)) {
				hasClickedOnImage = true;
				photoIndex = i;
				isLongExpo = false;
			}
		}
		
		//small -> large for long expo shots
		for (int i = 0; i < longExpoImageRects.size(); i++) {
			if (longExpoImageRects.get(i).contains(p)) {
				hasClickedOnImage = true;
				photoIndex = i;
				isLongExpo = true;
			}
		}
		
		//shrink back to grid-view of portfolio
		if (hasClickedOnImage && backButton.contains(p)) {
			hasClickedOnImage = false;
		}
	}
	
}
