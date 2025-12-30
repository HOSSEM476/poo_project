
public class MageTower extends Building {
    
    private String unitType;
    
    public MageTower() {
        super("Tour de Magie", 200);
        this.unitType = "Magicien";
    }
    
    public void produce() {
        System.out.println(getName() + " entraine des " + unitType + " pour " + player.getName());
    }
    
    public String getUnitType() {
        return unitType;
    }
}