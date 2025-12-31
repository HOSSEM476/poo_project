package units;
import GamePanelAndFrame.GamePanel;

public class Soldier extends Unit {
	GamePanel g;

    public Soldier() {
        super("Soldier", 55, 25, 10, 30);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }
        
        g.addCommentary("Soldier attacks " + target.getType() + " for " + damage + " damage.");

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Soldier";
    }
}
