package GamePanelAndFrame;

import javax.swing.JFrame;
import player.Player;
import units.Archer;
import units.Soldier;

public class Main {

    public static void main(String[] args) {
        // Create a new JFrame window for our game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app when window is closed
        window.setResizable(false); // Prevent resizing to avoid UI issues
        window.setTitle("2D Adventure"); // Set window title

        // Create the main game panel where everything will be drawn
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // Add the panel to the window
        window.pack(); // Resize window to fit preferred size of panel
        window.setLocationRelativeTo(null); // Center window on screen
        window.setVisible(true); // Show window

        // Create players and link them to the GamePanel
        Player human = new Player(false, gamePanel); // Human player
        Player ai = new Player(true, gamePanel);     // AI player

        // Start the game loop in a separate thread
        gamePanel.gameThread = new Thread(gamePanel);
        gamePanel.gameThread.start();

        // Demo: add commentary to show how text appears in the rectangle
        human.startTurn();               // Human turn started
        human.onEnemyUnitKilled(new Soldier()); // Human kills a Soldier
        ai.startTurn();                  // AI turn started
        ai.onEnemyUnitKilled(new Archer()); // AI kills an Archer
        human.endTurn();                 // Human turn ends
        ai.endTurn();                     // AI turn ends
    }
}
