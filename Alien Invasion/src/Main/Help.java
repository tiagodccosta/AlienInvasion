package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	
	public Rectangle backButton = new Rectangle(Game.WIDTH / 2 - 100, 600, 200, 80);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 40);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Hit the keys A,W,S,D for move the player!", 200, 320);
		g.drawString("Hit J to shoot the aliens!", 340, 420);
		
		g.setColor(new Color(105,205,95,120));
		g.fillRect(backButton.x, backButton.y, 200, 80);
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		
		g.drawString("Back", backButton.x + 40, backButton.y + 55);
		g2d.draw(backButton);
	}
	
}
