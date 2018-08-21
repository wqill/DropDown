package DropDown;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Graphics;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class PFade extends Platform {
	private Color color;
	private int INTERVAL = 400;
	public static final String IMG_FILE = "Images/ice.png";
	private static BufferedImage img;
	public static final int height = 15;


	public PFade(int posX, int posY, int width, int gridWidth, int gridHeight, Color color) {
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

	private Timer timer = new Timer(INTERVAL, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setPosY(-15);
		}
	});

	@Override
	public void perform(Player p){
		timer.start();
		super.perform(p);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawImage(img, this.getPosX(), this.getPosY()+10, this.getWidth(), height, null);

	}
}