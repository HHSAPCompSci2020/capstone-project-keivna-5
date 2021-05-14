package portfolio;

import java.util.ArrayList;

import drawingPackage.Constants;
import processing.core.PApplet;
import processing.core.PImage;
import worldSetting.Shutter;

public class Portfolio {
	Shutter shutter;
	private int y;
	
	public Portfolio() {
		shutter = new Shutter();
		y = 100;
	}
	
	public void draw(PApplet marker) {
		y = 100;
		int count = 0;
		ArrayList<PImage> images = Shutter.getallImages();
		marker.background(255);

		//display images in row in portfolio
		System.out.println("images.size: " + images.size());
		for (int i = 0; i < images.size(); i++) {

			PImage img = images.get(i);
			//images name from shutter are in format "index.png"
			img = marker.loadImage(i + ".png");
			
			//crop image to remove viewfinder
			int a = (int) Constants.viewfinderIndent;
			img.copy(a, a, marker.width-(2*a), marker.height-(2*a), 0, 0,  marker.width, marker.height);


			int b = i % 4;
			int c = i-b;
			int yMultiple = (c/4);
			
//			marker.image(img, 20 + (marker.width/4*i), y, marker.width/5, marker.height/5);
			marker.image(img, 20 + (marker.width/4*((i%4))), (150*yMultiple) + 50, marker.width/5, marker.height/5);

//			count++;
//			if (count == 4) {
//				System.out.println("adding 100 to y");
//				count = 0;
//				y += 100;
//			}
//			System.out.println("y in loop: " + y + ", count: " + count);
		}
	}
}
