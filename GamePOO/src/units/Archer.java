 package units;
import GamePanelAndFrame.GamePanel;

public class Archer extends Unit {
	GamePanel g;

    public Archer() {
        super("Archer", 45, 20, 5, 40);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }

        g.addCommentary("Archer shoots " + target.getType() + " for " + damage + " damage.");

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Archer";
    }
}
