package units;
import GamePanelAndFrame.GamePanel;

<<<<<<< HEAD
public class Wizard extends Unit {
	GamePanel g;
=======
import GamePanelAndFrame.GamePanel;
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project

public class Wizard extends Unit {
	GamePanel g;
    private static final int HEAL_AMOUNT = 25;

    public Wizard() {
        super("Wizard", 35, 0, 5, 80);
    }

    @Override
    public void attack(Unit target) {
<<<<<<< HEAD
 
=======

        if (!target.isAlive()) {
            // ❌ System.out.println
            // ✅ UI commentary
           g.addCommentary(
                "Wizard cannot heal a dead unit."
            );
            return;
        }

>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
        target.heal(HEAL_AMOUNT);

<<<<<<< HEAD
        g.addCommentary( "Wizard heals " + target.getType() +" for " + HEAL_AMOUNT + " HP.");
=======
        // ❌ System.out.println
        // ✅ UI commentary
        g.addCommentary(
            "Wizard heals " + target.getType() +
            " for " + HEAL_AMOUNT + " HP."
        );
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
    }

    @Override
    public String getType() {
        return "Wizard";
    }
}
