package buildings;

public class Barracks extends Building {
    
    private String unitType;
    
    public Barracks() {
        super("Caserne", 150);
        this.unitType = "Soldat";
    }
    
    public void produce(Building Building) {
        System.out.println(getName() + " entraine des " + unitType );
    }
    
    public String getUnitType() {
        return unitType;
    }
}