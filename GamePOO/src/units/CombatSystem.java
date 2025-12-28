package units;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CombatSystem {

    private List<Unit> units;

    public CombatSystem() {
        units = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void resolveAttack(Unit attacker, Unit target) {

        if (!attacker.isAlive()) {
            System.out.println(attacker.getType() + " is dead and cannot act.");
            return;
        }

        if (!target.isAlive()) {
            System.out.println(target.getType() + " is already dead.");
            return;
        }

        attacker.attack(target);

        if (!target.isAlive()) {
            System.out.println(target.getType() + " has been defeated.");
            units.remove(target);
        }
        public void getTileImage() {
            try { }}
        
        public void draw(Graphics2D g2) {
            for (Unit unit : units) {
                unit.draw(g2); // unit decides if it draws
            }
    }

    public List<Unit> getUnits() {
        return units;
    }
}
