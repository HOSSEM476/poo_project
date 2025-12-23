package player;

import java.util.ArrayList;
import java.util.List;
import buildings.Building;
import map.Tile;
import resources.ResourceManager;
import units.Unit;

public class Player {
		private int HP;
	    private List<Unit> units;
	    private int gold;
	    private List<Building> buildings;
	    private ResourceManager resourceManager;


	    public Player() {
	        this.units = new ArrayList<>();
	        this.gold = 100; 
	    }
	    

	    public void startTurn() {
	        System.out.println("player's turn has started.");
	    }

	    public void endTurn() {
	        System.out.println("player's turn has ended.");
	    }

	    public void trainUnit(Unit unit) {
	        units.add(unit);
	    }
	    public void addBuilding(Building building) {
	        buildings.add(building);
	    }

	    public void collectResources() {
	        //resourceManager.produce(buildings);
	    }


	    public List<Unit> getUnits() {
	        return units;
	    }

	    public int getGold() {
	        return gold;
	    }
	    public List<Building> getBuildings() {
	        return buildings;
	    }

	    public ResourceManager getResourceManager() {
	        return resourceManager;
	    }

	    public void addGold(int amount) {
	        gold += amount;
	    }

	    public void spendGold(int amount) {
	        if (gold >= amount) {
	            gold -= amount;
	        }else {
	        	System.out.println("Unsufficient funds!");	    }
	}
		public void deployUnit(Unit unit, Tile targetTile) {
			
		};
		public int getHP() {
			return HP;
		}
		public boolean isDefeated() {
			if(getHP() == 0) {
				return true;
			}else {
				return false;
			}
		}


		
}
