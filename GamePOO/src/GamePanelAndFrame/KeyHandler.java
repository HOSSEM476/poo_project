package GamePanelAndFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import buildings.Building;
import buildings.BuildingFactory;
import player.Player;
import player.TurnManager;
import units.Unit;

public class KeyHandler implements KeyListener {

    private GamePanel g;
    private TurnManager t;

    public KeyHandler(GamePanel g) {
        this.g = g;
    }

    public void updateTurnManager(TurnManager t) {
        this.t = t;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (t == null) return;
        int keyCode = e.getKeyCode();
        Player current = t.getCurrentPlayer();

        // ===== COMMENTARY SCROLL =====
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_PAGE_UP) g.scrollUp();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_PAGE_DOWN) g.scrollDown();

        // ===== TITLE SCREEN =====
        if (g.gameState == g.titelState) {
            if (keyCode == KeyEvent.VK_UP) g.UI.CommandNum = (g.UI.CommandNum == 0 ? 1 : 0);
            if (keyCode == KeyEvent.VK_DOWN) g.UI.CommandNum = (g.UI.CommandNum == 1 ? 0 : 1);
            if (keyCode == KeyEvent.VK_ENTER) {
                if (g.UI.CommandNum == 0) g.gameState = g.playState;
                else System.exit(0);
            }
            return;
        }

        // ===== PLAY STATE =====
        if (g.gameState == g.playState && !current.isAi()) {

            // --- BUILDING PHASE ---
            if (t.isAwaitingBuildingChoice()) {
                Building toBuild = null;
                switch (keyCode) {
                    case KeyEvent.VK_1: toBuild = BuildingFactory.createBuilding("Barracks", g); break;
                    case KeyEvent.VK_2: toBuild = BuildingFactory.createBuilding("ArcheryRange", g); break;
                    case KeyEvent.VK_3: toBuild = BuildingFactory.createBuilding("Stable", g); break;
                    case KeyEvent.VK_4: toBuild = BuildingFactory.createBuilding("Magetower", g); break;
                }

                if (toBuild != null) {
                    if (current.getGold() < toBuild.getCost()) {
                        g.addCommentary("Not enough gold to build " + toBuild.getName());
                    } else {
                        current.addBuilding(toBuild);
                        current.spendGold(toBuild.getCost());
                        g.addCommentary("Built " + toBuild.getName());
                    }
                }

                // After building, return to action phase
                t.setAwaitingBuildingChoice(false);
                t.setAwaitingAction(true);
                g.addCommentary("Choose an action: 1=Attack, 2=Build, 3=Train");
                return;
            }

            // --- UNIT TRAINING PHASE ---
            if (t.isAwaitingUnitChoice()) {
                Unit unit = null;
                switch (keyCode) {
                    case KeyEvent.VK_1: unit = TurnManager.createUnitByChoice(1); break;
                    case KeyEvent.VK_2: unit = TurnManager.createUnitByChoice(2); break;
                    case KeyEvent.VK_3: unit = TurnManager.createUnitByChoice(3); break;
                    case KeyEvent.VK_4: unit = TurnManager.createUnitByChoice(4); break;
                }

                if (unit != null) {
                    String requiredBuilding = TurnManager.getRequiredBuilding(unit);
                    if (!current.hasBuilding(requiredBuilding)) {
                        g.addCommentary("Cannot train " + unit.getName() + "! Need " + requiredBuilding);
                    } else if (current.getFood() < unit.getCost()) {
                        g.addCommentary("Not enough food to train " + unit.getName());
                    } else {
                        current.trainUnit(unit);
                        current.spendFood(unit.getCost());
                        g.addCommentary("Trained " + unit.getName());
                    }
                }
                // After training, return to action phase
                t.setAwaitingUnitChoice(false);
                t.setAwaitingAction(true);
                g.addCommentary("Choose an action: 1=Attack, 2=Build, 3=Train");
                return;
            }

            // --- ACTION PHASE ---
            if (t.isAwaitingAction()) {
                switch (keyCode) {
                    case KeyEvent.VK_1: // Attack
                        if (current.getUnits().isEmpty()) {
                            g.addCommentary("No units available to attack. Train units first.");
                        } else {
                            g.addCommentary("Press ENTER to attack (Wizard heals allies).");
                        }
                        return;
                    case KeyEvent.VK_2: // Build
                        g.addCommentary("Press 1-4 to build:");
                        g.addCommentary("1: Barracks, 2: ArcheryRange, 3: Stable, 4: Magetower");
                        t.setAwaitingBuildingChoice(true);
                        t.setAwaitingAction(false);
                        return;
                    case KeyEvent.VK_3: // Train
                        g.addCommentary("Press 1-4 to train units:");
                        g.addCommentary("1 = Soldier, 2 = Archer, 3 = Cavalry, 4 = Wizard");
                        t.setAwaitingUnitChoice(true);
                        t.setAwaitingAction(false);
                        return;
                }
            }

            // --- ATTACK / END TURN ---
            if (keyCode == KeyEvent.VK_ENTER) {
                t.attackPhase(current); // attack first enemy / heal for Wizard
                t.nextTurn();           // turn ends after attack/heal
            }

            // ESCAPE returns to title
            if (keyCode == KeyEvent.VK_ESCAPE) {
                g.gameState = g.titelState;
                g.UI.CommandNum = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
