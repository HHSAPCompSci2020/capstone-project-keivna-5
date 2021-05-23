package worldSetting;
import java.util.ArrayList;

/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

import java.awt.*;

import com.jogamp.newt.opengl.GLWindow;

import processing.core.*;

/**
 * The class has been modified to not take in mouse interactions
 * @author jrc03c This class represents the camera on the screen that you can use
 *         to move/look around
 * @author Elise and Katia
 */
public class CameraNoMouse {

	private PVector center, right, forward, up, position, velocity;
	private float pan, tilt, fov, viewDistance, speed, friction;
	private Point mouse, pMouse;

	/**
	 * Creates the "perspective" of the user and is used to move around the 3D world
	 * @param speed not sure
	 * @param friction not sure
	 * @param fov not sure
	 * @param viewDistance Not sure
	 */
	public CameraNoMouse(float speed, float friction, float fov, float viewDistance) {
		this.speed = speed;
		this.friction = friction;
		this.fov = fov;
		this.viewDistance = viewDistance;

		position = new PVector(0f, 0f, 0f);
		right = new PVector(1f, 0f, 0f);
		up = new PVector(0f, 1f, 0f);

		forward = new PVector(0f, 0f, 1f);
		velocity = new PVector(0f, 0f, 0f);

		pan = 0;
		tilt = 0;
	}

	/**
	 * Creates the camera by setting the perspective of the PApplent
	 * @param g can't be null
	 */
	public void setup(PApplet g) {
		g.perspective(fov, (float) g.width / (float) g.height, 0.01f, viewDistance);
	}

	/**
	 * Draws the window of the Camera, the view of the view finder
	 * @param g can't be null
	 */
	public void draw(PApplet g) {
		// Get the coordinates of the borders of the window
		int top = ((GLWindow) g.getSurface().getNative()).getY();
		int left = ((GLWindow) g.getSurface().getNative()).getX();
		int windowRight = g.width + left;
		int bottom = g.height + top;

		mouse = MouseInfo.getPointerInfo().getLocation();

		if (pMouse == null)
			pMouse = new Point(mouse.x, mouse.y);

		// tan of pi/2 or -pi/2 is undefined so if it happens to be exactly that increase it so the code works
		if (tilt == PConstants.PI / 2)
			tilt += 0.001f;
		if (tilt == -PConstants.PI / 2)
			tilt -= 0.001f;

		//Vector representing what forward is relative to the camera right now
		forward = new PVector(PApplet.cos(pan), PApplet.tan(tilt), PApplet.sin(pan));

		// make it a unit vector because the direction is all that matters
		forward.normalize();

		// subtract pi/2 from pan to get the vector perpendicular to forward to show
		// which way is right
		right = new PVector(PApplet.cos(pan - PConstants.PI / 2), 0, PApplet.sin(pan - PConstants.PI / 2));

		// account for friction
		velocity.mult(friction);
		// use velocity to find out location of new position
		position.add(velocity);
		// center of the sketch is in the direction of forward but translated based on
		// how you moved so you need to take into account position
		center = PVector.add(position, forward);
		g.camera(position.x, position.y, position.z, center.x, center.y, center.z, 0, 1, 0);

	}
	
	public float getX() { return position.x; }
	public float getY() { return position.y; }
	public float getZ() { return position.z; }

	/**
	 * Move the x position of the camera, takes into account speed
	 * @param dir the amount to move by
	 */
	public void moveX(int dir) { velocity.add(PVector.mult(right, speed * dir)); }

	/**
	 * Move the y position of the camera, takes into account speed
	 * @param dir the amount to move by
	 */
	public void moveY(int dir) { velocity.add(PVector.mult(up, speed * dir)); }
	
	/**
	 * Move the z position of the camera, takes into account speed
	 * @param dir the amount to move by
	 */
	public void moveZ(int dir) { velocity.add(PVector.mult(forward, speed * dir)); }
	
	/**
	 * Gets the viewDistance of the camera
	 * @return the viewDistance of the camera
	 */
	public float getViewDistance() { return viewDistance; }

	/**
	 * Sets the viewDistance of the camera
	 * @param distance the new distance of the camera
	 */
	public void setViewDistance(float distance) { viewDistance = distance; }
	
	/**
	 * Gets the angle of the camera
	 * @return the angle of the camera
	 */
	public float getPan() { return pan; }
	
	/**
	 * Sets the angle of the camera
	 * @param angle the new angle of the camera
	 */
	public void setPan(double angle) { pan = (float) angle; }
	
	/**
	 * Gets the position of the camera
	 * @return the x, y and z position of the camera
	 */
	public PVector getPosition() { return position; }
	
	public void setTilt(double angle) { tilt = (float) angle; }
	
	public float getTilt() { return tilt; }
	
	/**
	 * Sets the position of the player to the given coordinates
	 * @param x x-coordinate of where to move the player
	 * @param y y-coordinate of where to move the player
	 * @param z z-coordinate of where to move the player
	 */
	public void moveTo(float x, float y, float z) {
		this.getPosition().x = x;
		this.getPosition().y = y;
		this.getPosition().z = z;
	}
}
