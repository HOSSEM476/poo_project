package GamePanelAndFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import buildings.Building;
import buildings.BuildingFactory;
import player.Player;
import player.TurnManager;
import resources.ResourceManager;

public class KeyHandler implements KeyListener {

    private GamePanel g;
    private TurnManager t;

    public KeyHandler(GamePanel g) {
        this.g = g;
    }

    // Called from Main after TurnManager creation
    public void updateTurnManager(TurnManager t) {
        this.t = t;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        // ===== COMMENTARY SCROLL =====
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_PAGE_UP) {
            g.commentaryScroll--;
            if (g.commentaryScroll < 0) g.commentaryScroll = 0;
        }

        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_PAGE_DOWN) {
            g.commentaryScroll++;
            int totalLines = g.commentaryText.split("\n").length;
            if (g.commentaryScroll > totalLines - g.commentaryLinesVisible) {
                g.commentaryScroll = Math.max(0, totalLines - g.commentaryLinesVisible);
            }
        }

        // ===== TITLE STATE =====
        if (g.gameState == g.titelState) {

            if (keyCode == KeyEvent.VK_UP) {
                g.UI.CommandNum--;
                if (g.UI.CommandNum < 0) g.UI.CommandNum = 1;
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                g.UI.CommandNum++;
                if (g.UI.CommandNum > 1) g.UI.CommandNum = 0;
            }

            if (keyCode == KeyEvent.VK_ENTER) {
                if (g.UI.CommandNum == 0) {
                    g.gameState = g.playState;
                } else {
                    System.exit(0);
                }
            }
            return;
        }

        // ===== PLAY STATE =====
        if (g.gameState == g.playState && t != null) {
            Player current = t.getCurrentPlayer();
            if (t.isAwaitingUnitChoice()) {
                switch (keyCode) {
                    case KeyEvent.VK_1: t.humanTrainUnit(1); break;
                    case KeyEvent.VK_2: t.humanTrainUnit(2); break;
                    case KeyEvent.VK_3: t.humanTrainUnit(3); break;
                    case KeyEvent.VK_4: t.humanTrainUnit(4); break;
                    case KeyEvent.VK_ENTER: t.nextTurn(); break;
                    default: return; // Ignore other keys
                }
                t.setAwaitingUnitChoice(false);
                t.setAwaitingAction(false);
                t.nextTurn();
                return;
            }
         // Inside your key handling method
            if (t.isAwaitingBuildingChoice()) {
                Building toBuild = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1: toBuild = BuildingFactory.createBuilding("Barracks"); break;
                    case KeyEvent.VK_2: toBuild = BuildingFactory.createBuilding("ArcheryRange"); break;
                    case KeyEvent.VK_3: toBuild = BuildingFactory.createBuilding("Stable"); break;
                    case KeyEvent.VK_4: toBuild = BuildingFactory.createBuilding("Magetower"); break;
                }
                if (toBuild != null) {
                	if(current.getGold() >= toBuild.getCost()) {
                		current.addBuilding(toBuild); 
                		current.spendGold(toBuild.getCost());
                	}else {
                		g.addCommentary("you broke!");
                	}
                    t.setAwaitingBuildingChoice(false);
                    t.setAwaitingAction(false);
                    t.nextTurn();
                }
            }

            // Handle first-phase input: action choice
            if (t.isAwaitingAction()) {
                switch (keyCode) {
                    case KeyEvent.VK_1:
                        g.addCommentary("You chose to attack!");
                        // t.attack();
                        t.setAwaitingAction(false);
                        t.nextTurn();
                        break;
                    case KeyEvent.VK_2:
                        g.addCommentary("Press 1-4 to build:");
                        g.addCommentary("1: Barracks, 2: ArcheryRange, 3: Stable, 4: Magetower");
                        t.setAwaitingBuildingChoice(true); // You'll need to add this boolean to your TurnManager
                        break;
                    case KeyEvent.VK_3:
                        g.addCommentary("Press 1-4 to train units:");
                        g.addCommentary("1 = Soldier, 2 = Archer, 3 = Cavalry, 4 = Wizard");
                        t.setAwaitingUnitChoice(true);
                        break;
                }
                return;
            }
        }
            // Back to menu
            if (keyCode == KeyEvent.VK_ESCAPE) {
                g.gameState = g.titelState;
                g.UI.CommandNum = 0;
            }
        }
    

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}