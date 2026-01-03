package player;

import java.util.ArrayList;
import java.util.List;

import GamePanelAndFrame.GamePanel;
import buildings.Building;
import resources.ResourceManager;
import resources.ResourceType;
import units.Unit;

public class Player {

    private GamePanel g;                 // Reference to the game panel
    private List<Unit> units;            // Player units
    private List<Building> buildings;    // Player buildings
    private ResourceManager resourceManager; // Resource manager
    private int pts;                     // Points
    public boolean isAi;                 // AI flag
    private int unitsTrainedThisTurn = 0;
    private static final int MAX_UNITS_PER_TURN = 2;

    // Constructor
    public Player(boolean isAi, GamePanel g) {
        this.isAi = isAi;
        this.g = g;
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.resourceManager = new ResourceManager(g);
        this.pts = 0;
    }

    // ===== TURN START / END =====
    public void startTurn() {
        g.addCommentary((isAi ? "AI" : "Player") + "'s turn has started!");

        unitsTrainedThisTurn = 0; // 🔥 reset limit

        addGold(10);
        addFood(10);
        g.addCommentary((isAi ? "AI" : "Player") + " gained 10 Gold and 10 Food");

        collectResources();
    }



    public void endTurn() {
        g.addCommentary((isAi ? "AI" : "Player") + "'s turn has ended!");
    }

    // ===== UNITS =====
    public void trainUnit(Unit unit) {
        if (unit == null) return;

        // Limit per turn
        if (unitsTrainedThisTurn >= MAX_UNITS_PER_TURN) {
            g.addCommentary("You can only train " + MAX_UNITS_PER_TURN + " units per turn");
            return;
        }

        // Required building check
        String requiredBuilding = TurnManager.getRequiredBuilding(unit);
        if (!hasBuilding(requiredBuilding)) {
            g.addCommentary("Cannot train " + unit.getName() +
                            "! Need " + requiredBuilding);
            return;
        }

        // Food check
        if (getFood() < unit.getCost()) {
            g.addCommentary("Not enough food to train " + unit.getName());
            return;
        }

        // Train unit
        units.add(unit);
        spendFood(unit.getCost());
        unitsTrainedThisTurn++;

        g.addCommentary((isAi ? "AI" : "Player") +
                " trained " + unit.getName() +
                " (" + unitsTrainedThisTurn + "/" + MAX_UNITS_PER_TURN + ")");
    }

    public void attack(Unit attacker, Unit target) {
        if (attacker == null || target == null) return;

        g.addCommentary(attacker.getType() + " attacks " + target.getType());

        attacker.attack(target);

        if (!target.isAlive()) {
            removeUnit(target);
            onEnemyUnitKilled(target);
        }
    }


    public void heal(Unit healer, Unit target) {
        if (healer != null && target != null) {
            healer.attack(target); // Wizards override attack to heal
        }
    }

    public void removeUnit(Unit u) {
        units.remove(u);
    }

    public void onEnemyUnitKilled(Unit enemy) {
    	pts += 1;
        g.addCommentary((isAi ? "AI" : "Player") + " killed " + enemy.getName() + "! Points: " + pts);

        // ✅ Check win condition
        if (g.turnManager.getCurrentPlayer().getPoints() >= 10) {
            g.turnManager.endGame(isAi ? "AI" : "Player");
        }
    }

    public List<Unit> getUnits() {
        return units;
    }

    // ===== BUILDINGS =====
    public void addBuilding(Building b) {
        if (b != null) {
            buildings.add(b);
            g.addCommentary((isAi ? "AI" : "Player") + " built " + b.getName());
        }
    }

    public boolean hasBuilding(String buildingName) {
        for (Building b : buildings) {
            if (b.getClass().getSimpleName().equalsIgnoreCase(buildingName)) {
                return true;
            }
        }
        return false;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    // ===== RESOURCES =====
    public void addFood(int amount) {
        resourceManager.addResource(ResourceType.FOOD, amount);
    }

    public void addGold(int amount) {
        resourceManager.addResource(ResourceType.GOLD, amount);
    }

    public boolean spendGold(int amount) {
        return resourceManager.spendResource(ResourceType.GOLD, amount);
    }

    public boolean spendFood(int amount) {
        return resourceManager.spendResource(ResourceType.FOOD, amount);
    }

    public int getGold() {
        return resourceManager.getResource(ResourceType.GOLD);
    }

    public int getFood() {
        return resourceManager.getResource(ResourceType.FOOD);
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    // ===== RESOURCE COLLECTION =====
    public void collectResources() {
        for (Building b : buildings) {
            b.produce(); // Farm/Mine produce, others may log training
        }
    }

    // ===== GETTERS =====
    public boolean isAi() { return isAi; }
    public int getPoints() { return pts; }

    public GamePanel getG() { return g; }
    public void setG(GamePanel g) { this.g = g; }
}
