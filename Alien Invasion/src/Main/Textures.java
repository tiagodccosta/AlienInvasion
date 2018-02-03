package Main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage playerRight, bullet, enemy;
	
	private SpriteSheet ss;
	
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
		
	}
	
	public void getTextures() {
		playerRight = ss.grabImage(2, 1, 96, 96);
		bullet = ss.grabImage(7, 1, 64, 32);
		enemy = ss.grabImage(5, 1, 64, 64);
	}
}
