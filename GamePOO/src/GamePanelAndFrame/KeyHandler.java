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

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        // ================= TITLE STATE =================
        if (g.gameState == g.titelState) {

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                g.UI.CommandNum = (g.UI.CommandNum == 0) ? 1 : 0;
            }

            if (key == KeyEvent.VK_ENTER) {
                if (g.UI.CommandNum == 0) {
                    g.gameState = g.playState;
                    g.turnManager.startTurn();
                } else {
                    System.exit(0);
                }
            }
            return;
        }

        // ================= ESCAPE =================
        if (key == KeyEvent.VK_ESCAPE) {
            g.gameState = g.titelState;
            g.UI.CommandNum = 0;
            return;
        }

        // ================= PLAY STATE =================
        if (g.gameState != g.playState || t == null) return;

        Player current = t.getCurrentPlayer();
        if (current.isAi()) return; // AI handled automatically

        // ================= TRAIN UNITS =================
        if (t.isAwaitingUnitChoice()) {

            int choice = key - KeyEvent.VK_1 + 1;
            Unit u = TurnManager.createUnitByChoice(choice);

            if (u == null) return;

            String required = TurnManager.getRequiredBuilding(u);

            if (!current.hasBuilding(required)) {
                g.addCommentary("Need " + required);
            }
            else if (current.getFood() < u.getCost()) {
                g.addCommentary("Not enough food");
            }
            else {
                current.trainUnit(u);
            }

            t.showActionMenu();
            return;
        }

        // ================= BUILD =================
        if (t.isAwaitingBuildingChoice()) {

            Building b = switch (key) {
                case KeyEvent.VK_1 -> BuildingFactory.createBuilding("Barracks", g);
                case KeyEvent.VK_2 -> BuildingFactory.createBuilding("ArcheryRange", g);
                case KeyEvent.VK_3 -> BuildingFactory.createBuilding("Stable", g);
                case KeyEvent.VK_4 -> BuildingFactory.createBuilding("Magetower", g);
                default -> null;
            };

            if (b == null) return;

            if (current.getGold() >= b.getCost()) {
                current.addBuilding(b);
                current.spendGold(b.getCost());
            } else {
                g.addCommentary("Not enough gold");
            }

            t.showActionMenu();
            return;
        }

        // ================= ACTION MENU =================
        if (t.isAwaitingAction()) {

            switch (key) {

                // -------- ATTACK --------
                case KeyEvent.VK_1 -> {

                    Player enemy = (current == t.player1) ? t.player2 : t.player1;

                    if (current.getUnits().isEmpty()) {
                        g.addCommentary("No units to attack with");
                        return;
                    }

                    if (enemy.getUnits().isEmpty()) {
                        g.addCommentary("Enemy has no units");
                        return;
                    }

                    Unit attacker = current.getUnits().get(0);
                    Unit target = enemy.getUnits().get(0);

                    current.attack(attacker, target); // ✅ SINGLE SOURCE OF COMBAT

                    g.addCommentary("You attacked " + target.getName());

                    t.nextTurn(); // attack ends turn
                }

                // -------- BUILD --------
                case KeyEvent.VK_2 -> {
                    g.addCommentary("Build:");
                    g.addCommentary("1 Barracks | 2 Archery | 3 Stable | 4 Mage");
                    t.setAwaitingAction(false);
                    t.setAwaitingBuildingChoice(true);
                }

                // -------- TRAIN --------
                case KeyEvent.VK_3 -> {
                    g.addCommentary("Train:");
                    g.addCommentary("1 Soldier | 2 Archer | 3 Cavalry | 4 Wizard");
                    t.setAwaitingAction(false);
                    t.setAwaitingUnitChoice(true);
                }

                // -------- END TURN --------
                case KeyEvent.VK_ENTER -> t.nextTurn();
            }
        }
    }
}
