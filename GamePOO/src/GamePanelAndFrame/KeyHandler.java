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
        int keyCode = e.getKeyCode();

        // ===== COMMENTARY SCROLL =====
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_PAGE_UP) {
            g.commentaryScroll = Math.max(0, g.commentaryScroll - 1);
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_PAGE_DOWN) {
            int totalLines = g.commentaryText.split("\n").length;
            g.commentaryScroll = Math.min(totalLines - g.commentaryLinesVisible,
                                           g.commentaryScroll + 1);
        }

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
        if (g.gameState == g.playState && t != null) {
            Player current = t.getCurrentPlayer();

            if (!current.isAi()) { // Only player input

                // --- TRAINING PHASE ---
                if (t.isAwaitingUnitChoice()) {
                    int choice = (keyCode - KeyEvent.VK_1) + 1;
                    Unit unit = TurnManager.createUnitByChoice(choice);
                    if (unit != null) {
                        // Check if required building exists
                        String requiredBuilding = "";
                        switch (choice) {
                            case 1: requiredBuilding = "Barracks"; break;
                            case 2: requiredBuilding = "ArcheryRange"; break;
                            case 3: requiredBuilding = "Stable"; break;
                            case 4: requiredBuilding = "Magetower"; break;
                        }
                        if (!current.hasBuilding(requiredBuilding)) {
                            g.addCommentary("Cannot train " + unit.getName() + "! You need " + requiredBuilding);
                            return;
                        }
                        if (current.getFood() < unit.getCost()) {
                            g.addCommentary("Not enough food to train " + unit.getName());
                            return;
                        }
                        current.trainUnit(unit);
                        current.spendFood(unit.getCost());
                        g.addCommentary("You trained a " + unit.getName());
                        t.setAwaitingUnitChoice(false);
                        t.nextTurn(); // pass to AI
                    }
                    return;
                }

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
                        if (current.getGold() >= toBuild.getCost()) {
                            current.addBuilding(toBuild);
                            current.spendGold(toBuild.getCost());
                            g.addCommentary("You built " + toBuild.getClass().getSimpleName());
                            t.setAwaitingBuildingChoice(false);
                            t.nextTurn(); // switch to AI
                        } else {
                            g.addCommentary("Not enough gold to build " + toBuild.getClass().getSimpleName());
                        }
                    }
                    return;
                }

                // --- ACTION SELECTION PHASE ---
                if (t.isAwaitingAction()) {
                    switch (keyCode) {
                        case KeyEvent.VK_1: // ATTACK
                            if (current.getUnits().isEmpty()) {
                                g.addCommentary("Cannot attack! You have no units.");
                                return;
                            }
                            Player enemy = t.player1 == current ? t.player2 : t.player1;
                            if (!enemy.getUnits().isEmpty()) {
                                Unit target = enemy.getUnits().get(0); // pick first for simplicity
                                current.onEnemyUnitKilled(target);
                                enemy.getUnits().remove(target);
                                g.addCommentary("You attacked and killed " + target.getName());
                                t.setAwaitingAction(false);
                                t.nextTurn();
                            } else {
                                g.addCommentary("Enemy has no units to attack!");
                            }
                            break;

                        case KeyEvent.VK_2: // BUILD
                            g.addCommentary("Press 1-4 to build:");
                            g.addCommentary("1: Barracks, 2: ArcheryRange, 3: Stable, 4: Magetower");
                            t.setAwaitingBuildingChoice(true);
                            t.setAwaitingAction(false);
                            break;

                        case KeyEvent.VK_3: // TRAIN
                            if (current.getBuildings().isEmpty()) {
                                g.addCommentary("Cannot train! You have no buildings yet.");
                                return;
                            }
                            g.addCommentary("Press 1-4 to train units:");
                            g.addCommentary("1 = Soldier, 2 = Archer, 3 = Cavalry, 4 = Wizard");
                            t.setAwaitingUnitChoice(true);
                            t.setAwaitingAction(false);
                            break;
                    }
                }
            }
        }

        // ===== ESCAPE TO MENU =====
        if (keyCode == KeyEvent.VK_ESCAPE) {
            g.gameState = g.titelState;
            g.UI.CommandNum = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
