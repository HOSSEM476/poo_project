package player;

import java.util.ArrayList;
import java.util.List;

import GamePanelAndFrame.GamePanel;
import buildings.Building;
import resources.ResourceType;
import resources.ResourceManager;
import units.Unit;

public class Player {

    private GamePanel g;          // reference to GamePanel
    private int pts;
    private List<Unit> units;
    private List<Building> buildings;
    private ResourceManager resourceManager;
    private boolean isAi;

    // Constructor
    public Player(boolean isAi, GamePanel g) {
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.resourceManager = new ResourceManager();
        this.pts = 0;
        this.isAi = isAi;
        this.g = g;
    }

    public void startTurn() {
        g.addCommentary((isAi ? "AI" : "Player") + "'s turn has started.");
        collectResources();
    }

    public void endTurn() {
        g.addCommentary((isAi ? "AI" : "Player") + "'s turn has ended.");
    }

    public void trainUnit(Unit unit) {
        units.add(unit);
        g.addCommentary((isAi ? "AI" : "Player") +
                " trained a " + unit.getName());
    }

    public void onEnemyUnitKilled(Unit enemy) {
        pts += 1;
        g.addCommentary("Enemy unit killed! Points: " + pts);
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        g.addCommentary((isAi ? "AI" : "Player") +
                " built a " + building.getClass().getSimpleName());
    }

    public void collectResources() {
        for (Building building : buildings) {
            building.produce();
        }
        g.addCommentary((isAi ? "AI" : "Player") +
                " collected resources.");
    }

    public List<Unit> getUnits() {
        return units;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void addGold(int amount) {
        resourceManager.addResource(ResourceType.GOLD, amount);
    }

    public boolean spendGold(int amount) {
        return resourceManager.spendResource(ResourceType.GOLD, amount);
    }

    public int getGold() {
        return resourceManager.getResource(ResourceType.GOLD);
    }

    public boolean isAi() {
        return isAi;
    }

    public int getPoints() {
        return pts;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public int getFood() {
        return resourceManager.getResource(ResourceType.FOOD);
    }

    public boolean spendFood(int amount) {
        return resourceManager.spendResource(ResourceType.FOOD, amount);
    }
}
