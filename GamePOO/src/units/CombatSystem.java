package units;

import java.util.ArrayList;
import java.util.List;
import GamePanelAndFrame.GamePanel;

public class CombatSystem {
    private GamePanel g;
    private List<Unit> units;

    public CombatSystem(GamePanel g) {
        this.g = g;
        units = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        if (unit != null)
            units.add(unit);
    }

    public void resolveAttack(Unit attacker, Unit target) {
        if (!attacker.isAlive()) {
            if (g != null)
                g.addCommentary(attacker.getType() + " is dead and cannot act.");
            return;
        }

        if (!target.isAlive()) {
            if (g != null)
                g.addCommentary(target.getType() + " is already dead.");
            return;
        }

        attacker.attack(target);

        if (!target.isAlive()) {
            if (g != null)
                g.addCommentary(target.getType() + " has been defeated.");
            units.remove(target);
        }
    }

    public List<Unit> getUnits() {
        return units;
    }
}
