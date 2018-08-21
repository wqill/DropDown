package DropDown;

import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * Game object
 */
public class Player extends Object{
	public static final int SIZE = 29;
	public static final int INIT_X_POS = 100;
	public static final int INIT_Y_POS = 10;
	public static final int INIT_X_VEL = 0;
	public static final int INIT_Y_VEL = 1;
	private Color color;
	private boolean active;
	public static final String IMG_FILE = "Images/penguin.png";
	private static BufferedImage img;

	public Player(int gridWidth, int gridHeight, Color color, boolean active) {
		super(gridWidth, gridHeight, INIT_X_POS, INIT_Y_POS, SIZE, SIZE, INIT_X_VEL, INIT_Y_VEL);
		this.color = color;
		this.active = active;

		try {
			if (img == null) {
				img = ImageIO.read(new File(IMG_FILE));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean x) {
		active = x;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawImage(img, this.getPosX(), this.getPosY(), this.getWidth()+1, this.getHeight()+1, null);
	}
}
