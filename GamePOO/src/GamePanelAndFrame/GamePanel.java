package GamePanelAndFrame;

import GamePanelAndFrame.GamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import map.TileManager;
import player.Player;

public  class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
	public int commentaryScroll = 0;
	public int scrollTimer = 0;

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 13;
    int A;
    public String commentaryText = "";
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    Graphics2D g2;
    TileManager tileM = new TileManager(this);
    UI UI = new UI(this);
    KeyHandler keyH = new KeyHandler(this);

        Player p = new Player(false, this);
   int gameState = 0;
   int titelState = 1;
    int playState =3;
    Thread gameThread;
    
    
    public GamePanel() {
        
    	gameState = titelState;
    	// Set the size of the panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setFocusable(true);        // VERY IMPORTANT
        this.addKeyListener(keyH);      // register keyboard
        this.requestFocusInWindow();    // receive focus

        
    }
    
    

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    if(gameState == titelState) {
	    	UI.draw(g2);
	    }
	    else { 
	    tileM.draw(g2); 
        UI.draw(g2);}
	    
    
	   
	  //

	}


	public void addCommentary(String text) {
	    commentaryText += text + "\n";
	    repaint();
	}



	@Override
	public void run() {
	    while (gameThread != null) {
	        scrollTimer++;

	        if (scrollTimer > 60) {
	            scrollTimer = 0;
	            repaint();
	        }

	        try {
	            Thread.sleep(16); // ~60 FPS
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

		
	}





	
	



