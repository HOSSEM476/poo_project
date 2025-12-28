package GamePanelAndFrame;

import GamePanelAndFrame.GamePanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import map.TileManager;
import player.Player;
import units.CombatSystem;

public  class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
	
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    TileManager tileM = new TileManager(this);
    CombatSystem CS = new CombatSystem();
    Player p =new Player();
    
    Thread gameThread;
    
    
    public GamePanel() {
        // Set the size of the panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
    }
    
    
    
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    
	    tileM.draw(g2); 
        //CS.draw(g2);
        //p.draw(g2);

	}
	
	

}

