package buildings;

import GamePanelAndFrame.GamePanel;

public class ArcheryRange extends Building {

    private String unitType;

    public ArcheryRange(GamePanel g) {
        super("Archery Range", 20);
        this.unitType = "Archer";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType);
    }

    public String getUnitType() {
        return unitType;
    }
}