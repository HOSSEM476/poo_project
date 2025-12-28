package buildings;

public class Barracks extends Building {
    
    private String unitType;
    
    public Barracks() {
        super("Barrack", 150);
        this.unitType = "Soldier";
    }
    
    public void produce(){
        System.out.println(getName() + " trains a " + unitType );
    }
    
    public String getUnitType() {
        return unitType;
    }
}