package buildings;

public class Mine extends Building {
    
    private int goldProduction;
    
    public Mine() {
        super("Mine", 100);
        this.goldProduction = 15;
    }
    
    public void produce(Building Building) {
        System.out.println(getName() + " produit " + goldProduction + " or " );
    }
    
    public int getGoldProduction() {
        return goldProduction;
    }
}
