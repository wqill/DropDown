package DropDown;

import java.awt.Graphics;

/**
 * Object in game w/ position, size, bounds, and velocity.
 * Velocity controls movement. Position within bounds.
 */

public abstract class Object {
	/* coordinate given by upper left corner of object*/
	private int posX;
	private int posY;

	/* size of object */
	private int width;
	private int height;

	/* number of pixels to move */
	private int velX;
	private int velY;

	/* max x & y position for upper left corner */
	private int maxX;
	private int maxY;

	public Object (int gridWidth, int gridHeight, int posX, int posY, int width, int height, int velX, int velY) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		this.maxX = gridWidth - width;
		this.maxY = gridHeight - height;
	}

	/**
	 * How object should be drawn in GUI.
	 * 
	 * @param g Context used for drawing the object.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Keep object inside bound after moving.
	 */
	private void stayIn() {
		this.posX = Math.min(Math.max(this.posX, 0), this.maxX);
		this.posY = Math.min(Math.max(this.posY,  0), this.maxY);
	}

	/**
	 * Move object by velocity; can move out of bound.
	 */
	public void moveOut() {
		this.posX += this.velX;
		this.posY += this.velY;
	}

	/**
	 * Move object by velocity; stays inside bound.
	 */
	public void move() {
		this.posX += this.velX;
		this.posY += this.velY;
		stayIn();
	}

	/* getters & setters */
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public int getVelX() {
		return this.velX;
	}
	public int getVelY() {
		return this.velY;
	}
	public void setPosX(int posX) {
		this.posX = posX;
		stayIn();
	}
	public void setPosY(int posY) {
		this.posY = posY;
		stayIn();
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
}