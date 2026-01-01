package units;

public class Archer extends Unit {

    public Archer() {
        super("Archer", 45, 20, 5, 40);
    

       
    }

    @Override
    public void attack(Unit target) {
        int damage = Math.max(0, (attack - target.getDefense()));
        target.takeDamage(damage);
        // Optional: commentary handled in TurnManager or Player
    }

    @Override
    public String getType() {
        return "Archer";
    }
}
