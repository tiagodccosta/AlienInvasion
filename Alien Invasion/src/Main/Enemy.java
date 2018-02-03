package Main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Classes.EntityA;
import Classes.EntityB;

public class Enemy extends GameObject implements EntityB{

	private Textures tex;
	private static final int UP_WALL = 0;
	private static final int DOWN_WALL = 700;
	private static final int LEFT_WALL = 0;
	private static final int RIGHT_WALL = 1200;
	private int speedX = 5;
	private int speedY = 5;
	private Game game;
	private Controller c;

	Random r = new Random();
	
	public Enemy(double x, double y, Textures tex, Game game, Controller c) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.c = c;
	}
	
	public void tick() {
		
		 	x += speedX;
		    y += speedY;
		    if (x >= RIGHT_WALL - 50)
		    {
		        x = RIGHT_WALL - 50;
		        moveRandomDirection();
		    }
		    if (y > DOWN_WALL - 50)
		    {
		        y = DOWN_WALL - 50;
		        moveRandomDirection();
		    }
		    if (x <= LEFT_WALL + 0)
		    {
		        x = LEFT_WALL + 0;
		        moveRandomDirection();
		    }
		    if (y < UP_WALL + 0)
		    {
		        y = UP_WALL + 0;
		        moveRandomDirection();
		    }
		
		    for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Fisica.Collision(this, tempEnt)){
					c.removeEntity(tempEnt);
					c.removeEntity(this);
					game.setEnemy_killed(game.getEnemy_killed() + 1);
				}
				
			}
		    
	}
	
	public void moveRandomDirection(){
		double direction = Math.random() * 2.0 * Math.PI;
		double speed = 7;
		speedX = (int) (speed * Math.cos(direction));
		speedY = (int) (speed * Math.sin(direction));
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
