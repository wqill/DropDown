package DropDown;

import java.awt.*;
import java.util.Random;

public class PlatformGenerator {
	private static final int GRIDWIDTH = 300;
	private static final int GRIDHEIGHT = 400;

	public Platform next() {
		Random rng = new Random();
		Platform plat;

		int rngWidth = 50 + rng.nextInt(50);
		int px = rng.nextInt(200);
		int rngType = rng.nextInt(10);

		if (rngType < 3) {
			plat = new PFade(px, 390, rngWidth, GRIDWIDTH, GRIDHEIGHT, Color.BLUE);
		} else if (rngType < 4) {
			plat = new PSpike(px, 390, rngWidth, GRIDWIDTH, GRIDHEIGHT, Color.RED);
		} else {
			plat = new PSolid(px, 390, rngWidth, GRIDWIDTH, GRIDHEIGHT, Color.BLACK);
		}
		return plat;
	}
}
