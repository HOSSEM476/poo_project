package player;

import java.util.Random;
import GamePanelAndFrame.GamePanel;
import buildings.*;
import units.*;

public class TurnManager {

    private GamePanel g;
    public Player player1; // Human
    public Player player2; // AI
    private boolean isPlayer1Turn;

    private Random random = new Random();

    // Phase flags
    private boolean awaitingAction;
    private boolean awaitingUnitChoice;
    private boolean awaitingBuildingChoice;

    public TurnManager(Player player1, Player player2, GamePanel g) {
        this.player1 = player1;
        this.player2 = player2;
        this.g = g;
        isPlayer1Turn = true;
    }

    // ================= BASIC GETTERS =================
    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    public boolean isAwaitingAction() { return awaitingAction; }
    public boolean isAwaitingUnitChoice() { return awaitingUnitChoice; }
    public boolean isAwaitingBuildingChoice() { return awaitingBuildingChoice; }

    public void setAwaitingAction(boolean v) { awaitingAction = v; }
    public void setAwaitingUnitChoice(boolean v) { awaitingUnitChoice = v; }
    public void setAwaitingBuildingChoice(boolean v) { awaitingBuildingChoice = v; }

    // ================= TURN FLOW =================
    public void startTurn() {
        Player current = getCurrentPlayer();
        current.startTurn();

        awaitingAction = false;
        awaitingUnitChoice = false;
        awaitingBuildingChoice = false;

        if (current.isAi()) {
            aiTakeAction(current);
            nextTurn();
        } else {
            showActionMenu();
        }
    }

    public void nextTurn() {
        getCurrentPlayer().endTurn();
        isPlayer1Turn = !isPlayer1Turn;
        startTurn();
    }

    public void showActionMenu() {
        awaitingAction = true;
        awaitingUnitChoice = false;
        awaitingBuildingChoice = false;

        g.addCommentary("Choose an action:");
        g.addCommentary("1 = Attack, 2 = Build, 3 = Train Units, ENTER = End Turn");
    }

    // ================= AI LOGIC =================
    private void aiTakeAction(Player ai) {
        ai.addGold(10);
        ai.addFood(10);

        // Build priority
        String[] buildings = {"Barracks", "ArcheryRange", "Stable", "Magetower"};
        for (String b : buildings) {
            if (!ai.hasBuilding(b) && ai.getGold() >= BuildingFactory.getBuildingCost(b)) {
                Building build = BuildingFactory.createBuilding(b, g);
                ai.addBuilding(build);
                ai.spendGold(build.getCost());
                g.addCommentary("AI built " + b);
                return;
            }
        }

        // Train
        for (int i = 1; i <= 4; i++) {
            Unit u = createUnitByChoice(i);
            if (ai.hasBuilding(getRequiredBuilding(u)) && ai.getFood() >= u.getCost()) {
                ai.trainUnit(u);
                ai.spendFood(u.getCost());
                g.addCommentary("AI trained " + u.getName());
                return;
            }
        }

        // Attack
        Player enemy = (ai == player1) ? player2 : player1;
        if (!ai.getUnits().isEmpty() && !enemy.getUnits().isEmpty()) {
            Unit target = enemy.getUnits().get(0);
            enemy.getUnits().remove(target);
            ai.onEnemyUnitKilled(target);
            g.addCommentary("AI killed " + target.getName());
        }
    }

    // ================= HELPERS =================
    public static Unit createUnitByChoice(int choice) {
        return switch (choice) {
            case 1 -> new Soldier();
            case 2 -> new Archer();
            case 3 -> new Cavalry();
            case 4 -> new Wizard();
            default -> null;
        };
    }

    public static String getRequiredBuilding(Unit u) {
        if (u instanceof Soldier) return "Barracks";
        if (u instanceof Archer) return "ArcheryRange";
        if (u instanceof Cavalry) return "Stable";
        if (u instanceof Wizard) return "Magetower";
        return "";
    }
}
