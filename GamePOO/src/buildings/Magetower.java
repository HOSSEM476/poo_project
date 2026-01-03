package buildings;

import GamePanelAndFrame.GamePanel;

public class Magetower extends Building {

    private String unitType;

    public Magetower(GamePanel g) {
        super("Mage Tower", 90);
        this.unitType = "Mage";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType + " for ");
    }

    public String getUnitType() {
        return unitType;
    }
}