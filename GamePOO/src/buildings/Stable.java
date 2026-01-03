package buildings;

import GamePanelAndFrame.GamePanel;

public class Stable extends Building {

    private String unitType;

    public Stable(GamePanel g) {
        super("Stable", 70);
        this.unitType = "Cavalry";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType);
    }

    public String getUnitType() {
        return unitType;
    }
}