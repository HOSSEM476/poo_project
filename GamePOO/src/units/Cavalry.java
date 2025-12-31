<<<<<<< HEAD
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
=======
package units;

import GamePanelAndFrame.GamePanel;

public class Cavalry extends Unit {
   GamePanel g;
    public Cavalry() {
        super("Cavalry", 120, 30, 15, 90);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }

        // ❌ System.out.println removed
        // ✅ Send message to in-game UI instead
        g.addCommentary(
            "Cavalry charges " + target.getType() +
            " for " + damage + " damage."
        );

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Cavalry";
    }
}
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
