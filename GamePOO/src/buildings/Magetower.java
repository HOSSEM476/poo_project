package buildings;
public class Magetower extends Building {

    private String unitType;

    public Magetower() {
        super("Mage Tower", 200);
        this.unitType = "Mage";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType + " for ");
    }

    public String getUnitType() {
        return unitType;
    }
}