package portfolio;

import java.util.ArrayList;

import processing.core.PImage;

public class LongExpoPhoto {
	private int shutterSpeed;
	private ArrayList<PImage> photos;
	
	
	public LongExpoPhoto(int shutterSpeed, ArrayList<PImage> photos) {
		this.shutterSpeed = shutterSpeed;
		this.photos = photos;
		
	}
	
	public int getShutterSpeed() {
		return shutterSpeed;
	}
	
	public ArrayList<PImage> getPhotos(){
		return photos;
	}
}
