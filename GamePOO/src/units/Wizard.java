package units;
import GamePanelAndFrame.GamePanel;

public class Wizard extends Unit {
	GamePanel g;
    private static final int HEAL_AMOUNT = 25;

    public Wizard() {
        super("Wizard", 35, 0, 5, 80);
    }

    @Override
    public void attack(Unit target) {
        if (!target.isAlive()) {
            // ❌ System.out.println
            // ✅ UI commentary
           g.addCommentary("Wizard cannot heal a dead unit.");
            return;
        }
        target.heal(HEAL_AMOUNT);
        // ❌ System.out.println
        // ✅ UI commentary
        g.addCommentary(
            "Wizard heals " + target.getType() +
            " for " + HEAL_AMOUNT + " HP."
        );
    }

    @Override
    public String getType() {
        return "Wizard";
    }
}
