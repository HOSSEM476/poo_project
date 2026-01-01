package buildings;

import GamePanelAndFrame.GamePanel;

public class Barracks extends Building {
    
    private String unitType;
    
    public Barracks(GamePanel g) {
        super("Barrack", 1);
        this.unitType = "Soldier";
    }
    
    public void produce(){
        System.out.println(getName() + " trains a " + unitType );
    }
    
    public String getUnitType() {
        return unitType;
    }
}