 package units;
import GamePanelAndFrame.GamePanel;

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

<<<<<<< HEAD
        g.addCommentary("Archer shoots " + target.getType() + " for " + damage + " damage.");
=======
        // ❌ System.out.println
        // ✅ UI commentary
        g.addCommentary(
            "Archer shoots " + target.getType() +
            " for " + damage + " damage."
        );
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Archer";
    }
}
