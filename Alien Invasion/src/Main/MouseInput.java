package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.Game.STATE;


public class MouseInput implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		/**
		public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 200, 200, 80);
		public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 100, 350, 200, 80);
		public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 100, 500, 200, 80);
	*/
		
		if(Game.State != STATE.GAME) {
			//Play
			if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
				if(my >= 200 && my <= 280) {
					Game.State = STATE.GAME;
				}
			}
			
			//Help
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 350 && my <= 430) {
							Game.State = STATE.HELP;
					}
				}
			//Exit
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 500 && my <= 580) {
						System.exit(1);
					}
				}
			//Back
				if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100) {
					if(my >= 600 && my <= 680) {
						Game.State = STATE.MENU;
					}
				}
		}
		
		
				
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

}
