package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import Classes.EntityA;
import Classes.EntityB;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	public final String titulo = "ALIEN INVASION";
	public int frames = 0;
	public int updates = 0;

	
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage spritesheet = null;
	private BufferedImage background = null;
	private BufferedImage back = null;
	
	private PlayerRight pr;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private Help help;
	
	private boolean isShooting = false;
	
	public static int enemy_count = 5;
	public static int enemy_killed = 0;
	
	public static int Health = 300;
	public static int Score = 0;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	Graphics g;
	
	public static enum STATE {
		MENU,
		HELP,
		GAME
	};
	
	public static STATE State = STATE.MENU;
		
	public void init() {
		requestFocus();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spritesheet = loader.loadImage("/SpriteSheet.png");
			background = loader.loadImage("/backg2.jpg");
			back 	   = loader.loadImage("/back3.jpg"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		
		tex = new Textures(this);
		
		pr = new PlayerRight( 200, 200, tex, this);
		c = new Controller(tex, this);
		menu = new Menu();
		help = new Help();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.createEnemy(enemy_count);
		
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				frames++;
				delta--;
			}
			render();
			updates++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		if(State == STATE.GAME){
			pr.tick();
			c.tick();
		}

		if(enemy_killed >= enemy_count){
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////////////////////////////////////		
		g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
		
		if(State == STATE.GAME) {	
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

		g.setColor(Color.GRAY);
		g.fillRect(10, 620, 300, 70);
		
		g.setColor(Color.RED);
		g.fillRect(10, 620, Health, 70);
		
		g.setColor(Color.white);
		g.drawRect(10, 620, 300, 70);
		
		Font font0 = new Font("arial", Font.BOLD, 30);
		g.setFont(font0);
		g.setColor(Color.BLACK);
		g.drawString("SCORE: " + Score, 10, 30);
		
		g.drawString("FPS: " + frames + "  Ticks: " + updates, 900, 30);
			
		c.render(g);	
		pr.render(g);
		}else if(State == STATE.MENU) {
			menu.render(g);
		}else if(State == STATE.HELP) {
			help.render(g);
		}
		////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
		
	}
 
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
		
		if(key == KeyEvent.VK_W) {
			pr.setVelY(-7);
		}else if(key == KeyEvent.VK_S) {
			pr.setVelY(7);
		}else if(key == KeyEvent.VK_A) {
			pr.setVelX(-7);
		}else if(key == KeyEvent.VK_D) {
			pr.setVelX(7);
		}else if(key == KeyEvent.VK_J && !isShooting) {
			isShooting = true;
			c.addEntity(new Bullet(pr.getX() + 57, pr.getY() + 43, tex, this));
		}else if(key == KeyEvent.VK_ESCAPE) {
			Game.State = STATE.MENU;
		}
	}
}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			pr.setVelY(0);
		}else if(key == KeyEvent.VK_S) {
			pr.setVelY(0);
		}else if(key == KeyEvent.VK_A) {
			pr.setVelX(0);
		}else if(key == KeyEvent.VK_D) {
			pr.setVelX(0);
		}else if(key == KeyEvent.VK_J) {
			isShooting = false;
		}
	}
	
	private synchronized void start() {
		if(running) 
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	

	public static void main(String[] args) {
		
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame =new JFrame(game.titulo);
		
		frame.add(game);

		frame.setUndecorated(true);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spritesheet;
	}
	
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		Game.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		Game.enemy_killed = enemy_killed;
	}

	
}
