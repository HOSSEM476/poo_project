package buildings;

public class Stable extends Building {
    
    private String unitType;
    
    public Stable() {
        super("Ecurie", 180);
        this.unitType = "Cavalier";
    }
    
    public void produce() {
        System.out.println(getName() + " entraine des " + unitType + " pour " );
    }
    
    public String getUnitType() {
        return unitType;
    }
}