package GamePanelAndFrame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import map.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
   
    
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public GamePanel() {
        // Set the size of the panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Set background color to black
        this.setBackground(Color.red);
        // Enabling double buffering can improve game rendering performance
        this.setDoubleBuffered(true);
    }
    
    public void startgameThread() {
     gameThread = new Thread(this);
     gameThread.start();
    }
	@Override
	public void run() {
		
		
	}
}
