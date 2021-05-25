package photography;

import java.util.ArrayList;

import processing.core.PImage;

/**
 * represents a single long exposure photo
 * @author Elise
 */
public class LongExpoPhoto {
	private int shutterSpeed;
	private ArrayList<PImage> photos;
	
	/**
	 * initializes photos and shutterspeed fields
	 * @param shutterSpeed
	 */
	public LongExpoPhoto(int shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
		photos = new ArrayList<PImage>();
	}
	
	/**
	 * add a single shot to the photos that make up 1 long exposure photo
	 * @param photo
	 */
	public void addPhoto(PImage photo) {
		photos.add(photo);
	}
	
	/**
	 * @return shutter speed of this long exposure photo
	 */
	public int getShutterSpeed() {
		return shutterSpeed;
	}
	
	/**
	 * @return an arraylist of pimages of photos that make up the single long exposure photo
	 */
	public ArrayList<PImage> getAllPhotos(){
		return photos;
	}
	
	/**
	 * @return number of photos in the single long exposure photo
	 */
	public int numPhotos() {
		return photos.size();
	}
	
	/**
	 * @param index of the photo
	 * @return the PImage at the given index of the arraylist of images that make up the long exposure photo
	 */
	public PImage getPhoto(int index) {
		return photos.get(index);
	}
}
