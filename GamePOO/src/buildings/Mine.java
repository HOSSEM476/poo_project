package buildings;
import resources.ResourceManager;
import resources.ResourceType;
public class Mine extends Building {
	private ResourceManager manager;
    private int goldProduction;
    
    public Mine(ResourceManager manager) {
        super("Mine", 100);
        this.goldProduction = 50;
        this.manager = manager;
    }
    
    public void produce() {
        System.out.println(getName() + " produced " + goldProduction + " gold ");
        manager.addResource(ResourceType.GOLD, goldProduction);
    }
    
    public int getGoldProduction() {
        return goldProduction;
    }
}
