package units;

public class Wizard extends Unit {

    public Wizard() {
        super("Wizard", 60, 0, 100); // attack = 0
    }

    @Override
    public String getType() {
        return "Wizard";
    }

    // ❌ Wizard cannot attack
    @Override
    public void attack(Unit target) {
        System.out.println("Wizard cannot attack. Wizard can only heal.");
    }

    // ✅ Wizard special ability: heal allies
    public void healAlly(Unit ally) {
        if (!ally.isAlive()) {
            System.out.println("Cannot heal a dead unit.");
            return;
        }

        int healAmount = random.nextInt(20) + 15; // 15–34 HP
        ally.heal(healAmount);

        System.out.println(
            "Wizard heals " + ally.getType() +
            " for " + healAmount + " HP."
        );
    }
}