package Main;

import java.awt.Graphics;
import java.awt.Rectangle;

import Classes.EntityA;
import Classes.EntityB;

public class Bullet extends GameObject implements EntityA {

	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
	}
	
	public void tick() {
		x += 18;
		
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
				if(Fisica.Collision(this, tempEnt)){
					Game.Score++;
				}
			}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.bullet, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
}
