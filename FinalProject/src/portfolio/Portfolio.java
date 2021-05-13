package portfolio;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import worldSetting.Shutter;

public class Portfolio {
	//ArrayList<PImage> images;
	PImage img;
	PGraphics pg;
	Shutter shutter;
	
	public Portfolio() {
		//images = new ArrayList<PImage>();
		shutter = new Shutter();
	}
	
	public void setup(PApplet marker) {
//		img = marker.loadImage("photo-0138.png");
		//pg = createGraphics(80, 80, processing.core.P2D);
//		img = marker.loadImage("photo1.png");

	}
	
	//MAKE SHUTTER STATIC?!?!
	public void draw(PApplet marker) {
		
		ArrayList<PImage> images = Shutter.getallImages();
		//System.out.println("images in portfolio: " + images);
		marker.background(185, 230, 255);

		for (int i = 0; i < images.size(); i++) {
			PImage img = images.get(i);
			String name = i + ".png";
			img = marker.loadImage(name);
			marker.image(img, 100 + (marker.width/4*i), 100, marker.width/5, marker.height/5);

		}
		//int index = 0;
		//PImage img0 = images.get(index);
//		String name = img0.toString();
		//String name = index + ".png";
		//System.out.println("name of img0: " + name);
		
		
//		img = marker.loadImage("photo-0138.png");
		//img = marker.loadImage(name);

//		marker.background(185, 230, 255);
		//System.out.println("img: " + img.toString());
//		marker.image(img, 100, 100, marker.width/5, marker.height/5);
		
		//bridge.display(marker);
		//water.display(marker);
	}
}
