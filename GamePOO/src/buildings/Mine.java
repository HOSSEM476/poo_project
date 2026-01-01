package buildings;
import GamePanelAndFrame.GamePanel;
import resources.ResourceManager;
import resources.ResourceType;
public class Mine extends Building {
	GamePanel g;
	private ResourceManager manager;
    private int goldProduction;
    
    public Mine(ResourceManager manager) {
        super("Mine", 100);
        this.goldProduction = 50;
        this.manager = manager;
    }
    
    public void produce() {
        g.addCommentary(getName() + " produced " + goldProduction + " gold ");
        manager.addResource(ResourceType.GOLD, goldProduction);
    }
    
    public int getGoldProduction() {
        return goldProduction;
    }
}
