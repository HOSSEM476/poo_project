package GamePanelAndFrame;
import javax.swing.JFrame;

import player.Player;
import player.TurnManager;
import resources.ResourceType;
import units.*;
import buildings.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Player human = new Player(false);
        Player ai = new Player(true);
        TurnManager turnManager = new TurnManager(human, ai);

        // Add buildings to both players
        human.addBuilding(new Farm(human.getResourceManager()));
        human.addBuilding(new Mine(human.getResourceManager()));
        ai.addBuilding(new Farm(ai.getResourceManager()));
        ai.addBuilding(new Mine(ai.getResourceManager()));

        System.out.println("\n=== Game Start ===");
        turnManager.startGame();

        for (int round = 1; round <= 5; round++) {
            System.out.println("\n--- Round " + round + " ---");
            Player current = turnManager.getCurrentPlayer();

            if (current.isAi()) {
                System.out.println("AI's turn...");
                int choice = random.nextInt(3); // 0 = collect, 1 = train, 2 = kill

                switch (choice) {
                    case 0:
                        System.out.println("AI collected resources."); // already collected in startTurn()
                        break;
                    case 1:
                        Unit aiUnit = createUnitByChoice(random.nextInt(5) + 1);
                        if (aiUnit != null && current.getGold() >= aiUnit.getCost()) {
                            current.trainUnit(aiUnit);
                            current.spendGold(aiUnit.getCost());
                            System.out.println("AI trained a " + aiUnit.getName());
                        } else {
                            System.out.println("AI tried to train a unit but lacked gold.");
                        }
                        break;
                    case 2:
                        current.onEnemyUnitKilled(new Soldier());
                        break;
                }

            } else {
                boolean turnOver = false;
                while (!turnOver) {
                	current.getResourceManager().printResources();
                    System.out.println("\nYour turn! Choose an action:");
                    System.out.println("1. Train unit");
                    System.out.println("2. Kill enemy unit (+1 pt, +5 gold)");
                    System.out.println("3. End turn");

                    String input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            System.out.println("Choose a unit to train:");
                            System.out.println("1. Soldier (20 gold)");
                            System.out.println("2. Archer (25 gold)");
                            System.out.println("3. Cavalry (35 gold)");
                            System.out.println("4. Wizard (20 gold)");

                            int unitChoice = Integer.parseInt(scanner.nextLine());
                            Unit unit = createUnitByChoice(unitChoice);

                            if (unit != null && current.getGold() >= unit.getCost()) {
                                current.trainUnit(unit);
                                current.spendGold(unit.getCost());
                                System.out.println("You trained a " + unit.getName());
                            } else {
                                System.out.println("Invalid choice or not enough gold.");
                            }
                            break;

                        case "2":
                            current.onEnemyUnitKilled(new Soldier());
                            break;

                        case "3":
                            turnOver = true;
                            break;

                        default:
                            System.out.println("Invalid input. Try again.");
                    }
                }
            }

            
            turnManager.nextTurn();
        }

        System.out.println("\n=== Game Over ===");
        System.out.println("Human points: " + human.getPoints());
        System.out.println("AI points: " + ai.getPoints());
    }

    // ✅ Moved outside main method
    public static Unit createUnitByChoice(int choice) {
        switch (choice) {
            case 1: return new Soldier();
            case 2: return new Archer();
            case 3: return new Cavalry();
            case 4: return new Wizard();
            default: return null;
        }
    }
}
 /* 
public class main {

    public static void main(String[] args) {
        
      JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        // Causes window to be sized to fit the preferred size of GamePanel
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    	Player player1, player2;
    	
}*/