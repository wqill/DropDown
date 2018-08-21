package DropDown;

import java.awt.*;
import java.awt.Graphics;

public abstract class Platform extends Object{
	public static final int INIT_X_VEL = 0;
	public static final int INIT_Y_VEL = -1;
	public static final int HEIGHT = 10;
	private Color color;

	public Platform(int width, int posX, int posY, int gridWidth, int gridHeight, Color color) {
		super(gridWidth, gridHeight, posX, posY, width, HEIGHT, INIT_X_VEL, INIT_Y_VEL);
		this.color = color;
	}

	public void perform(Player p) {
		if (p != null) {
			p.setVelY(this.getVelY());
		}
	}

	public boolean inGrid() {
		return (super.getPosY() >= -10);
	}

	public boolean hasPlayer(Player p) {
		if (p == null) return false;
		int center = p.getPosX() + (int)(p.getWidth()/2);
		int leftEdge = this.getPosX();
		int rightEdge = leftEdge + this.getWidth();
		return center > leftEdge 
				&& center < rightEdge 
				&& (p.getPosY() + 20) >= (this.getPosY() - 1)
				&& (p.getPosY() + 20) <= (this.getPosY() + 1);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}
}
