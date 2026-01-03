package GamePanelAndFrame;
import javax.swing.JFrame;
import player.Player;
import player.TurnManager;

public class Main {

    public static void main(String[] args) {
        // Create the game window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("SILENT TACTICS");

        // Create the main game panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Create players
        Player human = new Player(false, gamePanel);
        Player ai = new Player(true, gamePanel);
       
        // Give starting gold
        human.addGold(100);
        ai.addGold(100);
        human.addFood(100);
        ai.addFood(100);
        
        // Create TurnManager
        gamePanel.turnManager = new TurnManager(human, ai, gamePanel);
        gamePanel.keyH.updateTurnManager(gamePanel.turnManager);


        // Start game loop
        gamePanel.gameThread = new Thread(gamePanel);
        gamePanel.gameThread.start();

        // Start the first turn
        TurnManager t= new TurnManager(human, ai, gamePanel) ;
        // human.getResourceManager().printResources();
        t.startTurn();

        /* Instructions in commentary
        gamePanel.addCommentary("Press 1-4 to train units:");
        gamePanel.addCommentary("1 = Soldier, 2 = Archer, 3 = Cavalry, 4 = Wizard");
        gamePanel.addCommentary("Press ENTER to end your turn.");*/
    }
   }
