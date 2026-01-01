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

            if (key == KeyEvent.VK_UP) {
                g.UI.CommandNum = (g.UI.CommandNum == 0) ? 1 : 0;
            }
            if (key == KeyEvent.VK_DOWN) {
                g.UI.CommandNum = (g.UI.CommandNum == 1) ? 0 : 1;
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
        if (current.isAi()) return;

        // -------- TRAIN --------
        if (t.isAwaitingUnitChoice()) {
            int choice = key - KeyEvent.VK_1 + 1;
            Unit u = TurnManager.createUnitByChoice(choice);

            if (u != null) {
                String req = TurnManager.getRequiredBuilding(u);
                if (!current.hasBuilding(req)) {
                    g.addCommentary("Need " + req);
                } else if (current.getFood() < u.getCost()) {
                    g.addCommentary("Not enough food");
                } else {
                    current.trainUnit(u);
                    current.spendFood(u.getCost());
                    g.addCommentary("You trained " + u.getName());
                }
            }
            t.showActionMenu();
            return;
        }

        // -------- BUILD --------
        if (t.isAwaitingBuildingChoice()) {
            Building b = switch (key) {
                case KeyEvent.VK_1 -> BuildingFactory.createBuilding("Barracks", g);
                case KeyEvent.VK_2 -> BuildingFactory.createBuilding("ArcheryRange", g);
                case KeyEvent.VK_3 -> BuildingFactory.createBuilding("Stable", g);
                case KeyEvent.VK_4 -> BuildingFactory.createBuilding("Magetower", g);
                default -> null;
            };

            if (b != null) {
                if (current.getGold() >= b.getCost()) {
                    current.addBuilding(b);
                    current.spendGold(b.getCost());
                    g.addCommentary("You built " + b.getClass().getSimpleName());
                } else {
                    g.addCommentary("Not enough gold");
                }
            }
            t.showActionMenu();
            return;
        }

        // -------- ACTION MENU --------
        if (t.isAwaitingAction()) {
            switch (key) {

                case KeyEvent.VK_1 -> { // ATTACK
                    Player enemy = (current == t.player1) ? t.player2 : t.player1;

                    if (current.getUnits().isEmpty()) {
                        g.addCommentary("No units to attack");
                        return;
                    }
                    if (enemy.getUnits().isEmpty()) {
                        g.addCommentary("Enemy has no units");
                        return;
                    }

                    Unit attacker = current.getUnits().get(0);
                    Unit target = enemy.getUnits().get(0);

                    current.attack(attacker, target);
                    g.addCommentary("You attacked " + target.getName());

                    if (!target.isAlive()) {
                        enemy.getUnits().remove(target);
                        current.onEnemyUnitKilled(target);
                        g.addCommentary("You killed " + target.getName());
                    }

                    t.nextTurn(); // attack ENDS turn
                }

                case KeyEvent.VK_2 -> {
                    g.addCommentary("Build:");
                    g.addCommentary("1 Barracks | 2 Archery | 3 Stable | 4 Mage");
                    t.setAwaitingAction(false);
                    t.setAwaitingBuildingChoice(true);
                }

                case KeyEvent.VK_3 -> {
                    g.addCommentary("Train:");
                    g.addCommentary("1 Soldier | 2 Archer | 3 Cavalry | 4 Wizard");
                    t.setAwaitingAction(false);
                    t.setAwaitingUnitChoice(true);
                }

                case KeyEvent.VK_ENTER -> t.nextTurn();
            }
        }
    }
}
