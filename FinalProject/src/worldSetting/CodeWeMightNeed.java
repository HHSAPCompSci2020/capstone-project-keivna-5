package worldSetting;

import java.awt.event.MouseEvent;

import processing.core.PApplet;

/**
 * Just some code we might use some time
 * @author Katia
 *
 */
public class CodeWeMightNeed {
	
	private void createElements(int size) {
		Element[][] elements = new Element[size][size];
		Element start;
	
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				float x = i * 5;
				float y = 0;
				float z = j * 5;
				elements[i][j] = new Element(x, y, z, 5);
//				b.add(elements[i][j]);
			}
		}
		start = elements[0][0];
	}
	
	private double random(double lower, double upper) {
		return Math.random() * (upper - lower) + lower;
	}
	
	public boolean isPointInCube(float x, float y, float z, float size) {
		// the x y z coords of the block are in the center so +/- by size/2 in all
		// directions to get the edges
		float left = x - size / 2;
		float right = x + size / 2;
		float top = y - size / 2;
		float bottom = y + size / 2;
		float front = z - size / 2;
		float back = z + size / 2;
		if (x > left && x < right && y > top && y < bottom && z > front && z < back) {
			return true;
		}

		return false;
	}
//	
//	public void mouseWheel(PApplet marker, MouseEvent event) {
//		float e = event.getCount();
//		marker.mouseEvent();
//		float e = event.getCount();
//		System.out.println(e);
//	}

}
