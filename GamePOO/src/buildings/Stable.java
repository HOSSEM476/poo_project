package buildings;

public class Stable extends Building {

    private String unitType;

    public Stable() {
        super("Stable", 180);
        this.unitType = "Cavalry";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType + " for ");
    }

    public String getUnitType() {
        return unitType;
    }
}