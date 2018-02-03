package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 200, 200, 80);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 100, 350, 200, 80);
	public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 100, 500, 200, 80);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 100);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("ALIEN INVASION", 200, 150);
		
		g.setColor(new Color(105,205,95,120));
		g.fillRect(playButton.x, playButton.y, 200, 80);
		g.fillRect(helpButton.x, helpButton.y, 200, 80);
		g.fillRect(exitButton.x, exitButton.y, 200, 80);

		
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		
		g.drawString("Play", playButton.x + 50, playButton.y + 55);
		g2d.draw(playButton);
		
		g.drawString("Help", helpButton.x + 50, helpButton.y + 55);
		g2d.draw(helpButton);
		
		g.drawString("Exit", exitButton.x + 50, exitButton.y + 55);
		g2d.draw(exitButton);

	}
	
}
