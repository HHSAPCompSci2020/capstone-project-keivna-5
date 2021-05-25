package portfolio;

import java.awt.Point;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import drawingPackage.DrawingSurface;
import photography.LongExpoPhoto;
import photography.Viewfinder;
import processing.core.*;


/**
 * Represents the portfolio itself
 * @author elise
 */
public class Portfolio {
	
	private ArrayList<PImage> images;
	private ArrayList<Rectangle> imageRects;
	
	private ArrayList<LongExpoPhoto> longExposureImages;
	private ArrayList<Rectangle> longExpoImageRects;

	private boolean hasClickedOnImage;
	private boolean isLongExpo;

	private int photoIndex;
	private Rectangle backButton;
	
	private final int viewfinderIndent = 60;
	
	private PGraphics textBox = new PGraphics();
	private PVector textBoxPos = new PVector(20,20);
	private float scroll = 0;
	
	PGraphics textBox = new PGraphics();
	PVector textBoxPos = new PVector(20,20);
	float scroll = 0;
	
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
		
		longExposureImages = new ArrayList<LongExpoPhoto>();
		longExpoImageRects = new ArrayList<Rectangle>();
		photoIndex = 0;
		
		backButton = new Rectangle(width - 100, height/20, 60, height/20);
	}

	/**
	 * Draws a grid of photos from all of the photos captured in the viewfinder world, 
	 * or a single photo full screen if the user has clicked a photo
	 * @param marker
	 * @pre PApplet marker cannot be null
	 * @post the PApplet parameter is changed
	 */
	public void draw(PApplet marker, Viewfinder viewfinder) {
//		marker.background(255);
//		marker.text("Loading...", marker.width/2, marker.height/2);
		textBox = marker.createGraphics(200, 400, marker.P2D);
		if (!hasClickedOnImage) {
			drawPortfolioGrid(marker, viewfinder);
		} else {
			drawSingleFullScreenImg(marker, photoIndex, isLongExpo);
		}
	}
	
	private void drawPortfolioGrid(PApplet marker, Viewfinder viewfinder) {
//		marker.background(255);
//		marker.fill(0);
//		marker.text("Loading...", marker.width/2, marker.height/2);
		
		imageRects = new ArrayList<Rectangle>();
		longExpoImageRects = new ArrayList<Rectangle>();

		images = viewfinder.getAllSingleShotPhotos();
		longExposureImages = viewfinder.getAllLongExpoImages();
		
		if (longExposureImages.size() > 0 && longExposureImages.get(0).getPhoto(0).width == 0 ) {
			marker.background(255);
			marker.fill(0);
			marker.text("Loading...", marker.width/2, marker.height/2);
		}
		
		marker.background(255);
		marker.text("Click on a photo to expand it!  (will take more time for larger shutter speeds)", 20, 55);
		
		int currY = 75;

		if (longExposureImages.size() > 0) {
			marker.text("Long Exposure Shots: ", 20, currY);
			for (int a = 0; a < longExposureImages.size(); a++) {
				//find out how many rows down images needs to be at
				int b = a % 4; //remainder when divided by 4
				int c = a-b; //c is divisible by 4
				int yMultiple = (c/4);
				int x =  20 + (marker.width/4*((a%4)));
				currY = (150*yMultiple) + 100;

				int width = marker.width/5;
				int height = marker.height/5;
				
				drawSingleLongExpo(marker, a, x, currY, width, height);
				longExpoImageRects.add(new Rectangle(x, currY, width, height));		
				currY += height;
			}
		}

		if (images.size() > 0) {
			//spacing between long expo and simple shots
			currY += 40;
			
			int lastLongExpoY = currY;

			marker.tint(255); //undo from long expo
			marker.text("Simple Shots: ", 20 , currY);

			//display images in row in portfolio
			for (int i = 0; i < images.size(); i++) {
				//find out how many rows down images needs to be at
				//4 photos per row, go to next row after
				int b = i % 4; //remainder when divided by 4
				int c = i-b; //c is divisible by 4
				int yMultiple = (c/4);
				
				currY = (150*yMultiple) + lastLongExpoY + 20;			
				int x =  20 + (marker.width/4*((i%4)));
				int width = marker.width/5;
				int height = marker.height/5;
				
				drawSingleShot(marker, i, x, currY, width, height);
				imageRects.add(new Rectangle(x, currY, width, height));
			}
		}
		
//		textBox.background(0,0,0,0);  // Transparent background but you could have it coloured.
//		textBox.stroke(0,0,0);
//		textBox.fill(0,0,0,0);
//		textBox.rect(0, 0, textBox.width-1, textBox.height-1);  // Black rectangle around the outside.
//		textBox.textSize(24);
//		textBox.fill(0);
//		textBox.textAlign(marker.LEFT, marker.TOP);
//		textBox.text("Hello this is a text box and it should have lots of lines hopefully if I keep writing for long enough.  Words words words words words.", 0, scroll, textBox.width, textBox.height);
//		textBox.endDraw();
//		 
//		marker.image(textBox, textBoxPos.x, textBoxPos.y);
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
		//60 photos needs 10, divide by 6
		LongExpoPhoto longExpo = longExposureImages.get(index);
		
		double numPhotos = longExpo.numPhotos();
		double tintExtra = 20;

		if (longExpo.getShutterSpeed() == 5) {
			tintExtra = 18;
		} else if (longExpo.getShutterSpeed() == 10) {
			tintExtra = 15;
		} else if (longExpo.getShutterSpeed() == 20) {
			tintExtra = 10;
		} else if (longExpo.getShutterSpeed() == 60) {
			tintExtra = 0;
		}
		
		double tint = (255.0/numPhotos) + tintExtra;

		marker.tint(255, (int)tint); //opacity for layering for long expo
		
		for (int b = 0; b < longExpo.numPhotos(); b++) { //goes thru each pimage in the arrayList<pimage>
			
			PImage longExpoImg = longExpo.getPhoto(b);
			longExpoImg = marker.loadImage(index + "." + b + ".png");
			longExpoImg.copy(viewfinderIndent, viewfinderIndent, marker.width-(2*viewfinderIndent), marker.height-(2*viewfinderIndent), 0, 0,  marker.width, marker.height); //crop image
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
	
	public void mouseWheel(DrawingSurface marker, MouseEvent event) {
		System.out.println("port mouseWheel");
		float e = event.getClickCount();
		marker.mouseEntered();
		e = event.getClickCount();
		System.out.println(e);
//	  scroll -= event.getClickCount()*10;
//	  System.out.println("scrolled: " + scroll);
	}
	
}
