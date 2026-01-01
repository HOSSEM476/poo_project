package player;

import java.util.Random;

import GamePanelAndFrame.GamePanel;
import buildings.Building;
import buildings.BuildingFactory;
import units.*;

public class TurnManager {

    private GamePanel g;
    public Player player1; // Human
    public Player player2; // AI
    private boolean isPlayer1Turn; // whose turn it is
    private Random random = new Random();

    // Phase flags
    private boolean isAwaitingAction = true;       // Choose: Attack / Build / Train
    private boolean awaitingBuildingChoice = false;
    private boolean awaitingUnitChoice = false;

    public TurnManager(Player player1, Player player2, GamePanel g) {
        this.player1 = player1;
        this.player2 = player2;
        this.g = g;
        this.isPlayer1Turn = true;
    }

    // ===== CURRENT PLAYER =====
    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    // ===== FLAG GETTERS/SETTERS =====
    public boolean isAwaitingAction() { return isAwaitingAction; }
    public void setAwaitingAction(boolean val) { isAwaitingAction = val; }

    public boolean isAwaitingBuildingChoice() { return awaitingBuildingChoice; }
    public void setAwaitingBuildingChoice(boolean val) { awaitingBuildingChoice = val; }

    public boolean isAwaitingUnitChoice() { return awaitingUnitChoice; }
    public void setAwaitingUnitChoice(boolean val) { awaitingUnitChoice = val; }

    // ===== TURN CONTROL =====
    public void startTurn() {
        Player current = getCurrentPlayer();
        current.startTurn();

        // Reset phase flags
        isAwaitingAction = !current.isAi(); // Human waits for input
        awaitingBuildingChoice = false;
        awaitingUnitChoice = false;

        if (current.isAi()) {
            aiTakeTurn(current);
        } else {
            g.addCommentary("Choose an action: 1 = Attack, 2 = Build, 3 = Train Units");
        }
    }

    // ===== AI LOGIC =====
    private void aiTakeTurn(Player ai) {
        // 1️⃣ Collect resources
        ai.addGold(10);
        ai.addFood(10);

        // 2️⃣ Build phase
        String[] buildingsPriority = {"Barracks", "ArcheryRange", "Stable", "Magetower"};
        for (String bName : buildingsPriority) {
            if (!ai.hasBuilding(bName) && ai.getGold() >= BuildingFactory.getBuildingCost(bName)) {
                Building b = BuildingFactory.createBuilding(bName, g);
                ai.addBuilding(b);
                ai.spendGold(b.getCost());
                g.addCommentary("AI built " + bName);
                break; // Only one action per phase
            }
        }

        // 3️⃣ Train phase
        String[] unitNames = {"Soldier", "Archer", "Cavalry", "Wizard"};
        for (int i = 1; i <= 4; i++) {
            Unit u = createUnitByChoice(i);
            String requiredBuilding = getRequiredBuilding(u);

            if (ai.hasBuilding(requiredBuilding) && ai.getFood() >= u.getCost()) {
                ai.trainUnit(u);
                ai.spendFood(u.getCost());
                g.addCommentary("AI trained " + u.getName());
                break; // Only one action per phase
            }
        }

        // 4️⃣ Attack phase
        attackPhase(ai);

        // End turn
        nextTurn();
    }

    // ===== ATTACK PHASE =====
    public void attackPhase(Player attacker) {
        Player defender = attacker == player1 ? player2 : player1;

        if (attacker.getUnits().isEmpty()) {
            g.addCommentary((attacker.isAi() ? "AI" : "Player") + " has no units to attack.");
            return;
        }

        // For AI: attack first enemy unit or heal with Wizard
        for (Unit u : attacker.getUnits()) {
            if (u instanceof Wizard) {
                // Heal self or ally if damaged
                Unit target = null;
                for (Unit ally : attacker.getUnits()) {
                    if (ally.getHp() < ally.getMaxHp()) {
                        target = ally;
                        break;
                    }
                }
                if (target != null) {
                    attacker.heal(u, target);
                    return;
                }
            } else {
                // Attack enemy
                if (!defender.getUnits().isEmpty()) {
                    Unit target = defender.getUnits().get(0); // First enemy unit
                    attacker.attack(u, target);
                    return;
                }
            }
        }
    }

    // ===== NEXT TURN =====
    public void nextTurn() {
        Player current = getCurrentPlayer();

        // Give passive resources
        current.addGold(10);
        current.addFood(10);

        current.endTurn();

        // Switch player
        isPlayer1Turn = !isPlayer1Turn;

        // Clear commentary for next turn
        if (isPlayer1Turn && g != null) g.clearCommentary();

        // Start next turn
        startTurn();
    }

    // ===== HUMAN TRAIN UNIT =====
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

    // ===== STATIC HELPERS =====
    public static Unit createUnitByChoice(int choice) {
        switch (choice) {
            case 1: return new Soldier();
            case 2: return new Archer();
            case 3: return new Cavalry();
            case 4: return new Wizard();
            default: return null;
        }
    }

    public static String getRequiredBuilding(Unit u) {
        if (u instanceof Archer) return "ArcheryRange";
        if (u instanceof Soldier) return "Barracks";
        if (u instanceof Cavalry) return "Stable";
        if (u instanceof Wizard) return "Magetower";
        return "";
    }
}
