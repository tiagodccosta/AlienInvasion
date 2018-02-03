package Main;

import java.awt.Graphics;
import java.awt.Rectangle;

import Classes.EntityA;
import Classes.EntityB;

public class PlayerRight extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	private Game game;
	
	public PlayerRight(double x, double y, Textures tex, Game game) {
		super(x,y);
		this.tex = tex;
		this.game = game;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= -10)
			x = -10;
		if(x >= 1200 -90)
			x = 1200 -90;
		if(y <= 0)
			y = 0;
		if(y >= 700 -85)
			y = 700 - 85;
		
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
				if(Fisica.Collision(this, tempEnt)){
					Game.Health -= 2;
				}
			}
		
		if(Game.Health <= 0){
			Game.State = Game.STATE.MENU;
			Game.Health = 300;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.playerRight, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
}
