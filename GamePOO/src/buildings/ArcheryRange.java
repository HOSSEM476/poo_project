package building
public class ArcheryRange extends Building {
    
    private String unitType;
    
    public ArcheryRange() {
        super("Champ de Tir", 120);
        this.unitType = "Archer";
    }
    
    public void produce() {
        System.out.println(getName() + " entraine des " + unitType + " pour " );
    }
    
    public String getUnitType() {
        return unitType;
    }
}