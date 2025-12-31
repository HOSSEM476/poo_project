package buildings;
public class ArcheryRange extends Building {

    private String unitType;

    public ArcheryRange() {
        super("Archery Range", 120);
        this.unitType = "Archer";
    }

    public void produce() {
        System.out.println(getName() + " trains " + unitType + " for ");
    }

    public String getUnitType() {
        return unitType;
    }
}