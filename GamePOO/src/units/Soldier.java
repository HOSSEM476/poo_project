package units;

public class Soldier extends Unit {

    public Soldier() {
        super("Soldier", 100, 20, 10, 50);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) {
            damage = 0;
        }
        
        System.out.println(
            "Soldier attacks " + target.getType() +
            " for " + damage + " damage."
        );

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Soldier";
    }
}
