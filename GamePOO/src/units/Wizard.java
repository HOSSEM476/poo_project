package units;

public class Wizard extends Unit {

    private static final int HEAL_AMOUNT  = 25;

    public Wizard() {
        super("Wizard", 60, 0, 5, 100);
    }

    @Override
    public void attack(Unit target) {
        if (!target.isAlive()) {
            System.out.println("Wizard cannot heal a dead unit.");
            return;
        }

        target.heal(HEAL_AMOUNT);

        System.out.println(
            "Wizard heals " + target.getType() +
            " for " + HEAL_AMOUNT + " HP."
        );
    }

    @Override
    public String getType() {
        return "Wizard";
    }
}
