package mazeexample;
import java.util.ArrayList;

import processing.core.*;

/**
 * 
 * @author asampath803 This class represents the player, a type of camera that
 *         is affected by gravity, collides with blocks
 */
public class Player extends Camera {
	private float w, h, d;
	private boolean grounded;
	private float gravity;

	public Player() {
		// speed is at .12f max
		this(1, 3, 1, .08f, .5f, .5f, .75f, PConstants.PI / 3f, 60f);
	}

	/**
	 * 
	 * @param w
	 *            Width of the player
	 * @param h
	 *            Height of the player
	 * @param d
	 *            Depth of the player
	 * @param speed
	 *            How fast the player moves
	 * @param xSensitivity
	 *            Mouse sensitivity on the x-axis
	 * @param ySensitivity
	 *            Mouse sensitivity on the y-axis
	 * @param friction
	 *            The amount of friction the player experiences while moving
	 * @param fov
	 *            The player's field of view
	 * @param viewDistance
	 *            How far the player can look in the distance
	 */
	public Player(float w, float h, float d, float speed, float xSensitivity, float ySensitivity, float friction,
			float fov, float viewDistance) {
		super(speed, xSensitivity, ySensitivity, friction, fov, viewDistance);
		this.w = w;
		this.h = h;
		this.d = d;
		grounded = true;
		gravity = 0.06f;
	}

	/**
	 * Checks to see if the player is colliding with any of the Block objects inside
	 * the specified ArrayList
	 * 
	 * @param blocks
	 *            ArrayList of Block objects to check collision with
	 */
	public void act(ArrayList<Block> blocks) {
		
	}

	public void jump() {
		if (grounded) {
			grounded = false;
			getVelocity().y -= 3f;
			getPosition().y -= .5;
		}
	}

	public float getWidth() {
		return w;
	}

	public float getHeight() {
		return h;
	}

	public float getDepth() {
		return d;
	}

	/**
	 * Sets the position of the player to the given coordinates
	 * 
	 * @param x
	 *            x-coordinate of where to move the player
	 * @param y
	 *            y-coordinate of where to move the player
	 * @param z
	 *            z-coordinate of where to move the player
	 */
	public void moveTo(float x, float y, float z) {
		this.getPosition().x = x;
		this.getPosition().y = y;
		this.getPosition().z = z;
	}
}
