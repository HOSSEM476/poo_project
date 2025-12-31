package GamePanelAndFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import map.TileManager;
import player.Player;
import player.TurnManager;

public class GamePanel extends JPanel implements Runnable {

    public int commentaryScroll = 0;
    public int scrollTimer = 0;

    final int originalTileSize = 16; 
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 13;

    public String commentaryText = "";
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Graphics2D g2;
    public TileManager tileM;
    public UI UI;
    public KeyHandler keyH;
    public TurnManager turnManager;

    public Player p;

    int gameState = 0;
    int titelState = 1;
   int playState =2;
    Thread gameThread;
	
	private boolean autoScroll = true; // auto-scroll enabled
	final int commentaryLinesVisible = 6; // visible lines

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setFocusable(true);

        // Initialize after GamePanel exists
        p = new Player(false, this);
        tileM = new TileManager(this);
        UI = new UI(this);
        keyH = new KeyHandler(this);

        this.addKeyListener(keyH);
        this.requestFocusInWindow();

        gameState = titelState;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;

        if (gameState == titelState) {
            UI.draw(g2);
        } else {
        	
            tileM.draw(g2);
            UI.draw(g2);
        }
    }

    public void addCommentary(String text) {
        commentaryText += text + "\n";
        if (autoScroll) {
            int totalLines = commentaryText.split("\n").length;
            commentaryScroll = Math.max(0, totalLines - commentaryLinesVisible);
        }
        repaint();
    }
    
    public void scrollUp() {
        commentaryScroll--;
        if (commentaryScroll < 0) commentaryScroll = 0;
        autoScroll = false; // stop auto-scroll when user scrolls up
        repaint();
    }

    public void scrollDown() {
        commentaryScroll++;
        int totalLines = commentaryText.split("\n").length;
        if (commentaryScroll >= totalLines - commentaryLinesVisible) {
            commentaryScroll = Math.max(0, totalLines - commentaryLinesVisible);
            autoScroll = true; // resume auto-scroll
        }
        repaint();
    }
    public void endTurn() {
        addCommentary((p.isAi ? "AI" : "Player") + "'s turn has ended.");
        commentaryText = "";
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
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




	
	