package player;

import java.util.Random;

import GamePanelAndFrame.GamePanel;
import buildings.*;
import units.*;

public class TurnManager {

    GamePanel g;
    public Player player1; // human
    public Player player2; // AI
    private boolean isPlayer1Turn;
    private Random random = new Random();

    // States
    private boolean isAwaitingAction = true;
    private boolean awaitingUnitChoice = false;
    private boolean awaitingBuildingChoice = false;

    public TurnManager(Player player1, Player player2, GamePanel g) {
        this.player1 = player1;
        this.player2 = player2;
        this.g = g;
        this.isPlayer1Turn = true;
    }

    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    public boolean isAwaitingAction() { return isAwaitingAction; }
    public void setAwaitingAction(boolean val) { isAwaitingAction = val; }
    public boolean isAwaitingUnitChoice() { return awaitingUnitChoice; }
    public void setAwaitingUnitChoice(boolean val) { awaitingUnitChoice = val; }
    public boolean isAwaitingBuildingChoice() { return awaitingBuildingChoice; }
    public void setAwaitingBuildingChoice(boolean val) { awaitingBuildingChoice = val; }

    // Start the turn
    public void startTurn() {
        Player current = getCurrentPlayer();

        // Reset flags
        setAwaitingAction(!current.isAi()); // Human waits for input, AI does not
        setAwaitingUnitChoice(false);
        setAwaitingBuildingChoice(false);

        current.startTurn(); // Adds commentary: "Player's turn started"

        if (current.isAi()) {
            // AI performs one action per turn
            aiTakeAction(current);

            // End AI turn
            current.endTurn();

            // Switch to next player
            isPlayer1Turn = !isPlayer1Turn;

            // Clear commentary if both players finished
         
            // Start next turn
            startTurn();
        } else {
            // Human turn
            g.addCommentary("Choose an action:");
            g.addCommentary("1 = Attack, 2 = Build, 3 = Train Units");
        }
    }
    private void aiTakeAction(Player ai) {
        ai.addGold(10);
        ai.addFood(10);

        // 1️⃣ Build priority buildings
        String[] buildingsPriority = {"Barracks", "ArcheryRange", "Stable", "Magetower"};
        for (String bName : buildingsPriority) {
            if (!ai.hasBuilding(bName) && ai.getGold() >= BuildingFactory.getBuildingCost(bName)) {
                Building b = BuildingFactory.createBuilding(bName, g);
                ai.addBuilding(b);
                ai.spendGold(b.getCost());
                g.addCommentary("AI built " + bName);
                return; // AI takes only 1 action per turn
            }
        }

        // 2️⃣ Train units
        for (int i = 1; i <= 4; i++) {
            Unit u = TurnManager.createUnitByChoice(i);
            String requiredBuilding = "";
            switch (i) {
                case 1: requiredBuilding = "Barracks"; break;
                case 2: requiredBuilding = "ArcheryRange"; break;
                case 3: requiredBuilding = "Stable"; break;
                case 4: requiredBuilding = "Magetower"; break;
            }
            if (ai.hasBuilding(requiredBuilding) && ai.getFood() >= u.getCost()) {
                ai.trainUnit(u);
                ai.spendFood(u.getCost());
                g.addCommentary("AI trained a " + u.getName());
                return;
            }
        }

        // 3️⃣ Attack
        Player enemy = ai == player1 ? player2 : player1;
        if (!ai.getUnits().isEmpty() && !enemy.getUnits().isEmpty()) {
            Unit target = enemy.getUnits().get(0);
            ai.onEnemyUnitKilled(target);
            enemy.getUnits().remove(target);
            g.addCommentary("AI attacked and killed " + target.getName());
            return;
        }

        // 4️⃣ Skip turn if nothing else
        g.addCommentary("AI skipped its turn (no actions available)");
    }


    // Move to next turn
    public void nextTurn() {
        Player current = getCurrentPlayer();

        // Give basic resources each turn
        current.addGold(10);
        current.addFood(10);

        // End turn
        current.endTurn();

        // Switch turn
        isPlayer1Turn = !isPlayer1Turn;

        // Clear commentary for next player
        if (isPlayer1Turn && g != null) g.clearCommentary();

        // Start next turn
        startTurn();
    }

    // Human trains a unit
    public void humanTrainUnit(int choice) {
        Player human = getCurrentPlayer();
        if (!human.isAi()) {
            Unit unit = createUnitByChoice(choice);
            if (unit != null) {
                if (!human.hasBuilding(getRequiredBuilding(unit))) {
                    g.addCommentary("Cannot train " + unit.getName() + "! Need " + getRequiredBuilding(unit));
                    return;
                }
                if (human.getFood() >= unit.getCost()) {
                    human.trainUnit(unit);
                    human.spendFood(unit.getCost());
                } else {
                    g.addCommentary("Not enough food to train " + unit.getName());
                }
            }
        }
    }

    // AI action
    private void aiAct(Player ai) {
        // 1️⃣ Collect resources
        ai.addGold(10);
        ai.addFood(10);

        // 2️⃣ Priority: ensure at least one building for each unit type
        String[] buildingsPriority = {"Barracks", "ArcheryRange", "Stable", "Magetower"};
        for (String bName : buildingsPriority) {
            if (!ai.hasBuilding(bName) && ai.getGold() >= BuildingFactory.getBuildingCost(bName)) {
                Building b = BuildingFactory.createBuilding(bName, g);
                ai.addBuilding(b);
                ai.spendGold(b.getCost());
                g.addCommentary("AI built " + bName);
                return; // only one action per turn
            }
        }

        // 3️⃣ Train units if there is a building and enough food
        String[] unitPriority = {"Soldier", "Archer", "Cavalry", "Wizard"};
        for (int i = 1; i <= 4; i++) {
            Unit u = TurnManager.createUnitByChoice(i);
            String requiredBuilding = "";
            switch (i) {
                case 1: requiredBuilding = "Barracks"; break;
                case 2: requiredBuilding = "ArcheryRange"; break;
                case 3: requiredBuilding = "Stable"; break;
                case 4: requiredBuilding = "Magetower"; break;
            }
            if (ai.hasBuilding(requiredBuilding) && ai.getFood() >= u.getCost()) {
                ai.trainUnit(u);
                ai.spendFood(u.getCost());
                g.addCommentary("AI trained a " + u.getName());
                return; // only one action per turn
            }
        }

        // 4️⃣ Attack if AI has at least one unit and player has units
        Player player = ai == player1 ? player2 : player1;
        if (!ai.getUnits().isEmpty() && !player.getUnits().isEmpty()) {
            Unit target = player.getUnits().get(0); // pick first unit
            ai.onEnemyUnitKilled(target);
            player.getUnits().remove(target);
            g.addCommentary("AI attacked and killed " + target.getName());
            return;
        }

        // If nothing possible, just skip turn
        g.addCommentary("AI skipped its turn (no actions available)");
    }


    // Helper: unit factory
    public static Unit createUnitByChoice(int choice) {
        switch (choice) {
            case 1: return new Soldier();
            case 2: return new Archer();
            case 3: return new Cavalry();
            case 4: return new Wizard();
            default: return null;
        }
    }

    // Helper: required building for a unit
    private String getRequiredBuilding(Unit u) {
        if (u instanceof Archer) return "ArcheryRange";
        if (u instanceof Soldier) return "Barracks";
        if (u instanceof Cavalry) return "Stable";
        if (u instanceof Wizard) return "Magetower";
        return "";
    }
}
