package portfolio;

import java.util.ArrayList;

import processing.core.PImage;

public class LongExpoPhoto {
	private int shutterSpeed;
	private ArrayList<PImage> photos;
	
	public LongExpoPhoto(int shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
		photos = new ArrayList<PImage>();
	}
	
	public void addPhoto(PImage photo) {
		photos.add(photo);
	}
	
	public int getShutterSpeed() {
		return shutterSpeed;
	}
	
	public ArrayList<PImage> getAllPhotos(){
		return photos;
	}
	
	public int numPhotos() {
		return photos.size();
	}
	
	public PImage getPhoto(int index) {
		return photos.get(index);
	}
}
