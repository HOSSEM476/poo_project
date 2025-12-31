package buildings;
import resources.ResourceManager;
import resources.ResourceType;

public class Farm extends Building {
    private ResourceManager manager;
    private int foodProduction;
    
    public Farm(ResourceManager manager) {
        super("Farm", 50);
<<<<<<< HEAD
        this.foodProduction = 35;
=======
        this.foodProduction = 20;
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
        this.manager = manager;
    }
    @Override
    public void produce() {
        System.out.println(getName() + " produced " + foodProduction + " food.");
        manager.addResource(ResourceType.FOOD, foodProduction);
    }
    
    public int getFoodProduction() {
        return foodProduction;
    }

}