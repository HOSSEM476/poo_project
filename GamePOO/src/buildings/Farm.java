package buildings;


public class Farm extends Building {
    
    private int foodProduction;
    
    public Farm() {
        super("Ferme", 50);
        this.foodProduction = 20;
    }
    
    public void produce( Building Building) {
        System.out.println(getName() + " produit " + foodProduction + " nourriture " );
    }
    
    public int getFoodProduction() {
        return foodProduction;
    }
}