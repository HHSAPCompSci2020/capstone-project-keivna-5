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
	private Water water;
	private Bridge bridge;
	private ArrayList<Car> cars;

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
		water = new Water(350, 350, 50, 5000);
		bridge = new Bridge(350, -500, 50, 200);
		cars = new ArrayList<Car>();
		
		for(int i = -8; i <= 8; i++) {
			cars.add(new Car(350 + (i * 45 * 2) + (float) (i * Math.random()), -560, 90, 45));
		}

		savedTime = marker.millis();
		SoundPlayer.playSeaSound();

	}

	/**
	 * Displays water and bridge and paints the sky
	 * Starts playing ocean sounds
	 * @param g can't be null
	 */
	public void draw(PApplet g, double ISOval, double lightSourceY, double lightSourceX) {
		g.background(sky[0], sky[1], sky[2]);
		g.smooth();
		g.lights();
		
		int ISOfor3D = (int)((ISOval/6400.0)*255.0);
		//System.out.println("ISOfor3D: " + ISOfor3D);
		g.ambientLight(ISOfor3D, ISOfor3D, ISOfor3D);


		double pointLightY = lightSourceY*g.height;
		
		if (lightSourceY < 0.1) {
			pointLightY = 0;
		}
		
		double pointLightX = lightSourceX*g.width;
		if (lightSourceX < 0.1) {
			pointLightX = 0;
		}
		System.out.println("g.height: " + g.height + ", pointLightY: " + pointLightY + ", lightSourceY: " + lightSourceY);
		
//		System.out.println("g.width: " + g.width + ", pointLightX: " + pointLightX + ", lightSourceX: " + lightSourceX);


//		g.pointLight(255, 255, 255, (float) pointLightX, (float) pointLightY, 400);
//		g.pointLight(255, 255, 255, g.width/2, g.height/2, 400);
//		g.pointLight(128, 128, 128, g.width/2, g.height/2, 400);
//		g.directionalLight(128, 128, 128, g.width/2, g.height/2, 400);
		g.directionalLight(128, 128, 128, (float) pointLightX, (float) pointLightY, 400);
//		g.directionalLight(128, 128, 128, (float) lightSourceX, (float) lightSourceY, 400);
//<<<<<<< Updated upstream
//
////		g.pointLight(255, 255, 255, g.width/2, g.height/2, 400);
//=======
//>>>>>>> Stashed changes

//		g.pointLight(255, 255, 255, g.width/2, g.height/2, 400);

//<<<<<<< Updated upstream
////		g.tint(150);
////		if (g.mousePressed) {
////			g.pointLight(128, 128, 128, g.width/2, (float) pointLightY, 400);
////		g.directionalLight(128, 128, 128, 0, -1, 0);
//
//=======
//
////		g.tint(150);
////		if (g.mousePressed) {
////			g.pointLight(128, 128, 128, g.width/2, (float) pointLightY, 400);
////		g.directionalLight(128, 128, 128, 0, -1, 0);
//
//<<<<<<< HEAD
//		if (g.mousePressed) {
//			g.pointLight(0, 255, 255, g.width/2, g.height/2, 400);
//		}
//		water.display(g);
//		bridge.display(g);	
//=======
//>>>>>>> Stashed changes
////			g.pointLight(255, 255, 255, g.width/2, g.height/4, 400);
//
////			g.tint(100);
//		//}
//		
//<<<<<<< Updated upstream
//=======
//>>>>>>> b1e2452aaffc08e3e11f7ad9e1a09f82e3848883
//>>>>>>> Stashed changes

		
		water.display(g);
		bridge.display(g);
		for(Car c: cars) {
			c.display(g);
		}

		//DO NOT DELETE!!! IDC HOW DESPERATELY YOU WANT TO DONT.
//		// Calculate how much time has passed
//		int passedTime = g.millis() - savedTime;
//		//the time is a factor of the duration of sea sound, play sound again
//		//currenlty doesn't work but it's fine bc sound plays for 4 minutes when window is opened
//		if (passedTime % Constants.seaSoundDurationMillis == 0) {
//			SoundPlayer.playSeaSound();
//
//			//System.out.println("1 minute has passed!");
//			//background(random(255)); // Color a new background
//			savedTime = g.millis(); // Save the current time to restart the timer!
//		}
		//SoundPlayer.playSeaSound();

		// Calculate how much time has passed
		int passedTime = g.millis() - savedTime;
		//the time is a factor of the duration of sea sound, play sound again
		//currently doesn't work but it's fine bc sound plays for 4 minutes when window is opened
		if (passedTime % seaSoundDurationMillis == 0) {
			SoundPlayer.playSeaSound();

			//System.out.println("1 minute has passed!");
			//background(random(255)); // Color a new background
			savedTime = g.millis(); // Save the current time to restart the timer!
		}
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
	
	
}
