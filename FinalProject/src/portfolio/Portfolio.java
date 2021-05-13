package portfolio;

import java.util.ArrayList;

import drawingPackage.Constants;
import processing.core.PApplet;
import processing.core.PImage;
import worldSetting.Shutter;

public class Portfolio {
	Shutter shutter;
	
	public Portfolio() {
		shutter = new Shutter();
	}
	
	public void draw(PApplet marker) {
		
		ArrayList<PImage> images = Shutter.getallImages();
		marker.background(255);

		//display images in row in portfolio
		for (int i = 0; i < images.size(); i++) {
			PImage img = images.get(i);
			//images name from shutter are in format "index.png"
			img = marker.loadImage(i + ".png");
			
			//crop image to remove viewfinder
			int a = (int) Constants.viewfinderIndent;
			img.copy(a, a, marker.width-(2*a), marker.height-(2*a), 0, 0,  marker.width, marker.height);

			marker.image(img, 100 + (marker.width/4*i), 100, marker.width/5, marker.height/5);
		}
	}
}
