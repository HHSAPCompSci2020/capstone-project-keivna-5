package worldSetting;

import java.util.ArrayList;

import processing.core.*;

/**
 * Creates the water, bridge and background for the world,
 * also controlling the sound that gets played
 * @author Katia and Elise
 *
 */
public class World { 

	private int[] sky;
	private Water backWater;
	private MovingWater water;
	private Bridge bridge;
	private Mountain mountain1;
	private Mountain mountain2;
	private ArrayList<Car> cars;
	private PImage background;
	private Element origin;
	
	private int numCars;

	int savedTime;
	int totalTime = 60000;

	/**
	 * Length of the seaSound
	 */
	public static final int seaSoundDurationMillis = 259000; //4:19 minutes

	/**
	 * Creates the world elements:
	 * water, bridge and sky
	 * @param marker can't be null
	 */
	public World(PApplet marker) {
		numCars = 4;
		sky = new int[] {185, 230, 255};
		backWater = new Water(350, 330, 50, 100000);
		water = new MovingWater(1350, 350, 50, 500);
		bridge = new Bridge(350, -500, 50, 200, 5);
		mountain1 = new Mountain(-350, -200, 200, 800);
		mountain2 = new Mountain(700, -200, 200, 800);
		cars = new ArrayList<Car>();
		for(int i = -numCars; i <= numCars; i++) {
			// different sides of the road
			cars.add(new Car(350 + (i * 45 * 5f) + (float) (i * Math.random()), -560, 85, 45, true, 350, 200));
			cars.add(new Car(350 + (i * 45 * 5f) + (float) (i * Math.random()), -560, 15, 45, false, 350, 200));
		}
		
		SoundPlayer.playSeaSound();
	}

	/**
	 * Displays water and bridge and paints the sky
	 * Starts playing ocean sounds
	 * @param g can't be null
	 */
	public void draw(PApplet g, double ISOval, double lightSourceY, double lightSourceX, BackgroundColor backgroundColor) {
		if (setSky(g, backgroundColor)) {
			g.background(sky[0], sky[1], sky[2]);
		} else {
			g.background(background);
		}

		g.smooth();
		g.lights();

		int ISOfor3D = (int)((ISOval/6400.0)*255.0);
		g.ambientLight(ISOfor3D, ISOfor3D, ISOfor3D);

		double pointLightY = lightSourceY*g.height;
		if (lightSourceY < 0.1) {
			pointLightY = 0;
		}

		double pointLightX = lightSourceX*g.width;
		if (lightSourceX < 0.1) {
			pointLightX = 0;
		}

		g.directionalLight(128, 128, 128, (float) pointLightX, (float) pointLightY, 400);

		backWater.display(g);
		//water.display(g);
		bridge.display(g);
		mountain1.display(g);
		mountain2.display(g);
		for(Car c: cars) {
			c.display(g);
		}
	}

	/**
	 * Sets the color of the sky
	 * @param color array of rgb colors
	 */
	public boolean setSky(PApplet g, BackgroundColor newBackground) {
		boolean isColor = false;
		String imageName = "media/sky-";	
		switch(newBackground) {
			case CLEAR_DAY:
				imageName += "day-clear.png";
				background = g.loadImage(imageName);
				break;
			case CLOUDY_DAY:
				imageName += "day-cloudy.png";
				background = g.loadImage(imageName);
				break;
			case CLOUDY_NIGHT:
				imageName += "night-cloudy.png";
				background = g.loadImage(imageName);
				break;
			case SUNSET_CLOUDY:
				imageName += "night-sunset.png";
				background = g.loadImage(imageName);
				break;
			case STARS_NIGHT:
				imageName += "night-stars.png";
				background = g.loadImage(imageName);
				break;
			case PUREBLACK:
				isColor = true;
				sky = new int[] {0,0,0};
				break;
			default:
				isColor = true;
				sky = new int[] {185, 230, 255};
				break;
		}
		return isColor;
	}

	/**
	 * represents the type of background images
	 * @author Elise and Katia
	 *
	 */
	enum BackgroundColor{
		CLEAR_DAY,
		CLOUDY_DAY,
		CLOUDY_NIGHT,
		SUNSET_CLOUDY,
		STARS_NIGHT,
		PUREBLACK
	}
}
