package player;

import java.util.ArrayList;
import java.util.List;
import buildings.Building;
import resources.ResourceType;
import resources.ResourceManager;
import units.Unit;

public class Player {
		private int pts;
	    private List<Unit> units;
	    private List<Building> buildings;
		private ResourceManager resourceManager;
		private boolean isAi;
	 
	    public Player(boolean isAi) {
	        this.units = new ArrayList<>();
	        this.buildings = new ArrayList<>();
	        this.resourceManager = new ResourceManager(); 
	        this.pts = 0; 
	        this.isAi = isAi;

	    }
	    public void startTurn() {
	        System.out.println((isAi ? "AI" : "Player") + "'s turn has started.");
	        collectResources();
	    }
	    public void endTurn() {
	    	  System.out.println((isAi ? "AI" : "Player") + "'s turn has ended.");
	    }
	    public void trainUnit(Unit unit) {
	        units.add(unit);
	    }
	    public void onEnemyUnitKilled(Unit enemy) {
	        pts += 1; 
	        System.out.println("Enemy unit killed! Points: " + pts);
	    }
	    public void addBuilding(Building building) {
	        buildings.add(building);
	    }
	    public void collectResources() {
	     for(Building building : buildings) {
	    	 building.produce();
	     }
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
