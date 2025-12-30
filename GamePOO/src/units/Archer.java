package units;

public class Archer extends Unit {

    public Archer() {
        super("Archer", 70, 25, 5, 60);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }

        System.out.println(
            "Archer shoots " + target.getType() +
            " for " + damage + " damage."
        );

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Archer";
    }
}