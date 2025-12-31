package units;
import GamePanelAndFrame.GamePanel;

public class Cavalry extends Unit {
	GamePanel g;

    public Cavalry() {
        super("Cavalry", 65, 30, 15 , 70);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }

        g.addCommentary("Cavalry charges " + target.getType() + " for " + damage + " damage.");

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Cavalry";
    }
}
