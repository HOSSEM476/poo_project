package Main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener {
    private GameMap gameMap;
    private Player player;

    public GamePanel() {
        this.gameMap = new GameMap(24, 18);
        this.player = new Player(5, 5, gameMap);
        
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int tileSize = gameMap.getTileSize();
        int[][] tiles = gameMap.getTiles();
        
        // Draw tiles
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                int tile = tiles[y][x];
                int screenX = x * tileSize;
                int screenY = y * tileSize;
                
                switch (tile) {
                    case GameMap.GRASS:
                        g.setColor(new Color(34, 139, 34));
                        break;
                    case GameMap.WATER:
                        g.setColor(new Color(30, 144, 255));
                        break;
                    case GameMap.TREE:
                        g.setColor(new Color(139, 69, 19));
                        break;
                    case GameMap.STONE:
                        g.setColor(new Color(128, 128, 128));
                        break;
                    default:
                        g.setColor(Color.BLACK);
                }
                
                g.fillRect(screenX, screenY, tileSize, tileSize);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(screenX, screenY, tileSize, tileSize);
            }
        }
        
        // Draw player
        int playerScreenX = player.getX() * tileSize;
        int playerScreenY = player.getY() * tileSize;
        g.setColor(Color.YELLOW);
        g.fillOval(playerScreenX + 4, playerScreenY + 4, tileSize - 8, tileSize - 8);
        g.setColor(Color.WHITE);
        g.drawOval(playerScreenX + 4, playerScreenY + 4, tileSize - 8, tileSize - 8);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.moveDown();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.moveRight();
                break;
        }
        
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
