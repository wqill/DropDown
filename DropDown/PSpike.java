package DropDown;

import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class PSpike extends Platform {

	private Color color;
	public static final String IMG_FILE = "Images/spikes.png";
	private static BufferedImage img;
	public static final int height = 15;

	public PSpike(int posX, int posY, int width, int gridWidth, int gridHeight, Color color) {
		super(width, posX, posY, gridWidth, gridHeight, color);
		this.color = color;

		try {
			if (img == null) {
				img = ImageIO.read(new File(IMG_FILE));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	@Override
	public void perform(Player p) {
		if (p != null) {
			p.setActive(false);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawImage(img, this.getPosX(), this.getPosY()+10, this.getWidth(), height, null);
	}
}
