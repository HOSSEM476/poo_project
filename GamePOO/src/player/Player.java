package player;


import java.util.ArrayList;
import java.util.List;

import GamePanelAndFrame.GamePanel;
import buildings.Building;
import resources.ResourceType;
import resources.ResourceManager;
import units.Archer;
import units.Cavalry;
import units.Soldier;
import units.Unit;
import units.Wizard;

public class Player {
    private GamePanel g;          // reference to GamePanel
    private int pts;
    private List<Unit> units;
    private List<Building> buildings;
    private ResourceManager resourceManager;
    public boolean isAi;

    // Constructor
    public Player(boolean isAi, GamePanel g) {
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.resourceManager = new ResourceManager(g);
        this.pts = 0;
        this.isAi = isAi;
        this.g = g;
    }


    public void startTurn() {
        if (g != null)
            g.addCommentary((isAi ? "AI" : "Player") + "'s turn has started.");
        collectResources();
    }

    public void endTurn() {
        if (g != null)
            g.addCommentary((isAi ? "AI" : "Player") + "'s turn has ended.");
    }

    public void trainUnit(Unit unit) {
        if (unit == null) return;

        // 1. Define the requirement
        String requiredBuilding = "";
        if (unit instanceof Archer) requiredBuilding = "ArcheryRange";
        else if (unit instanceof Soldier) requiredBuilding = "Barracks";
        else if (unit instanceof Cavalry) requiredBuilding = "Stable";
        else if (unit instanceof Wizard) requiredBuilding = "Magetower";

        // 2. Check the requirement
        if (!requiredBuilding.equals("") && !hasBuilding(requiredBuilding)) {
            if (g != null) g.addCommentary("Cannot train " + unit.getName() + "! Need " + requiredBuilding);
            return; // Stop the method here
        }

        // 3. If check passes, proceed with training (your original code)
        units.add(unit);
        if (g != null) {
            g.addCommentary((isAi ? "AI" : "Player") + " trained a " + unit.getName());
        }
    }
        public void showUnits() {
        	if (isAi() || units == null) return;
        	g.addCommentary("Trained Units:");
            for (Unit unit : units) {
                g.addCommentary(" -" + unit.getName() + "\n");
                
            }
        }
     public GamePanel getG() {
			return g;
		}


		public void setG(GamePanel g) {
			this.g = g;
		}


		// Helper to check if the player owns a specific building type
        public boolean hasBuilding(String buildingName) {
            for (Building b : buildings) {
                // This matches the logic you used in your addBuilding commentary
                if (b.getClass().getSimpleName().equalsIgnoreCase(buildingName)) {
                    return true;
                }
            }
            return false;
        }

    public void onEnemyUnitKilled(Unit enemy){
        pts += 1;
        if (g != null)
            g.addCommentary("Enemy unit killed! Points: " + pts);
    }

    public void addBuilding(Building building) {
        if (building == null) return;
        buildings.add(building);
        if (g != null)
            g.addCommentary((isAi ? "AI" : "Player") +
                    " built a " + building.getClass().getSimpleName());
    }

    public void collectResources() {
        for (Building building : buildings){
            building.produce();
        }
    }
    
    public List<Unit> getUnits() {
        return units;
    }


    public List<Building> getBuildings() {
        return buildings;
    }
    public void addFood(int amount) {
    	resourceManager.addResource(ResourceType.FOOD, amount);
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