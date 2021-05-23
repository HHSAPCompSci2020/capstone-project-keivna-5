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
//	private Water water;
	private MovingWater water;
	private Bridge bridge;
	private Mountain mountain;
	private ArrayList<Car> cars;
	PImage background;

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
		sky = new int[] {185, 230, 255};
		water = new MovingWater(350, 350, 50, 100);//new Water(350, 350, 50, 100000);
		bridge = new Bridge(350, -500, 50, 200, 5);
		mountain = new Mountain(350, -500, 50, 1000);
		cars = new ArrayList<Car>();

		for(int i = -8; i <= 8; i++) {
			cars.add(new Car(350 + (i * 45 * 2.5f) + (float) (i * Math.random()), -560, 85, 45, true, 350, 200));
		}

		for(int i = -8; i <= 8; i++) {
			cars.add(new Car(350 + (i * 45 * 2.5f) + (float) (i * Math.random()), -560, 15, 45, false, 350, 200));
		}

		savedTime = marker.millis();
		SoundPlayer.playSeaSound();
	}

	/**
	 * Displays water and bridge and paints the sky
	 * Starts playing ocean sounds
	 * @param g can't be null
	 */
	public void draw(PApplet g, double ISOval, double lightSourceY, double lightSourceX, BackgroundColor backgroundColor) {
		boolean isColor = false;
		String imageName = "media/sky-";
		
		switch(backgroundColor) {
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
		
//		background = g.loadImage(imageName);
		
		if (isColor) {
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


		water.display(g);
		bridge.display(g);
		mountain.display(g);
		for(Car c: cars) {
			c.display(g);
		}

		//viewfinder.draw(g);
	}

	/**
	 * Sets the camera to a specific position on the screen
	 * specifically at (100, -3000, 50)
	 * @param camera can't be null
	 */
	public void setPlayerAtStart(CameraNoMouse camera) {
		camera.moveTo(100, -3000, 50);
	}

	/**
	 * Sets the color of the sky
	 * @param color array of rgb colors
	 */
	public void setSky(int[] color) {
		sky = color;
	}

	enum BackgroundColor{
		CLEAR_DAY,
		CLOUDY_DAY,
		CLOUDY_NIGHT,
		SUNSET_CLOUDY,
		STARS_NIGHT,
		PUREBLACK
	}
}
