package GamePanelAndFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{

	private GamePanel g;

	public KeyHandler(GamePanel g) {
		this.g=g;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

	    int code = e.getKeyCode();

	    // ===== TITLE STATE =====
	    if (g.gameState == g.titelState) {

	        if (code == KeyEvent.VK_UP) {
	            g.UI.CommandNum--;
	            if (g.UI.CommandNum < 0) {
	                g.UI.CommandNum = 1;
	            }
	        }

	        if (code == KeyEvent.VK_DOWN) {
	            g.UI.CommandNum++;
	            if (g.UI.CommandNum > 1) {
	                g.UI.CommandNum = 0;
	            }
	        }

	        if (code == KeyEvent.VK_ENTER) {
	            if (g.UI.CommandNum == 0) {
	                g.gameState = g.playState; // start game
	            }
	            if (g.UI.CommandNum == 1) {
	                System.exit(0); // quit
	            }
	        }
	    }

	    // ===== PLAY STATE =====
	    else if (g.gameState == g.playState) {

	        if (code == KeyEvent.VK_ESCAPE) {
	            g.gameState = g.titelState; // back to menu
	            g.UI.CommandNum = 0;        // reset selector
	        }
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
