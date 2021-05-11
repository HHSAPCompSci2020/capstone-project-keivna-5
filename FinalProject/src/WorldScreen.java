
import processing.core.*;


import worldSetting.Camera;
import worldSetting.World;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WorldScreen extends Screen {

	private ArrayList<Integer> keys;

	private Camera camera;

	private Rectangle button;
	private World world;

	public float ratioX, ratioY;


	public WorldScreen() {
		super(800,600);

		button = new Rectangle(800/2-100,600/2-50,200,100);

		world = new World();
		camera = new Camera();

		keys = new ArrayList<Integer>();

	}

	//TODO: draw world + menu + tabs here
	public void draw(PApplet marker) {

		//System.out.println("drawing world screen");
		ratioX = (float)marker.width/this.DRAWING_WIDTH;
		ratioY = (float)marker.height/this.DRAWING_HEIGHT;

		marker.scale(ratioX, ratioY);
		marker.background(255,255,255);


		camera.draw(marker);
		world.display(marker);


		if (checkKey(KeyEvent.VK_W)) {
			System.out.println("W before");
			camera.moveZ(1);
			System.out.println("W after");

		} else if (checkKey(KeyEvent.VK_S)) {
			camera.moveZ(-1);
		}
		if (checkKey(KeyEvent.VK_A))
			camera.moveX(1);
		else if (checkKey(KeyEvent.VK_D))
			camera.moveX(-1);


		//switch tabs
		marker.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		marker.fill(0);
		String str = "Click me!";
		float w = marker.textWidth(str);
		marker.text(str, button.x+button.width/2-w/2, button.y+button.height/2);

		//		popStyle();
	}

	public void checkCamera() {
		//		if (checkKey(KeyEvent.VK_W)) {
		//			System.out.println("W before");
		//			camera.moveZ(1);
		//			System.out.println("W after");
		//
		//		} else if (checkKey(KeyEvent.VK_S)) {
		//			camera.moveZ(-1);
		//		}
		//		if (checkKey(KeyEvent.VK_A))
		//			camera.moveX(1);
		//		else if (checkKey(KeyEvent.VK_D))
		//			camera.moveX(-1);
	}


	public void setPlayerAtStart() {
		//camera.moveTo(start.getX(), start.getY()-15, start.getZ());
		camera.moveTo(350, 350, 50);

	}
	
	public void keyPressed(PApplet marker) {
		System.out.println("calling key pressed");

		if (!checkKey(marker.keyCode))
			keys.add(marker.keyCode);

		//		if (checkKey(KeyEvent.VK_SPACE))
		//			camera.jump();
	}

	// Removes key from array list
	public void keyReleased(PApplet marker) {
		while (checkKey(marker.keyCode))
			keys.remove(new Integer(marker.keyCode));
	}

	// Checks if given key code is in the array list
	public boolean checkKey(int i) {
		return keys.contains(i);
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	//TODO: turn into tabs here
	public void mousePressed(DrawingSurface marker) {
		System.out.println("calling mouse pressed from worldscreen");

		Point p = actualCoordinatesToAssumed(new Point(marker.mouseX,marker.mouseY));
		if (button.contains(p)) {
//			marker.switchScreen(ScreenSwitcher.SCREEN2);
			marker.switchScreen(1);

			System.out.println("switch screen");
		}
	}

	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

}

