package units;

public class Soldier extends Unit {

    public Soldier() {
        super("Soldier", 55, 25, 10, 30);
    }

    @Override
    public void attack(Unit target) {
        int randomFactor = random.nextInt(2) + 1;
        int damage = (attack - target.getDefense()) * randomFactor;

        if (damage < 0) damage = 0;

        target.takeDamage(damage);
    }

    @Override
    public String getType() {
        return "Soldier";
    }
}
