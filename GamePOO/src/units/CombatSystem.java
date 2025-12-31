package units;
public class CombatSystem {
    public static boolean resolveAttack(Unit attacker, Unit target) {
        attacker.attack(target);
        return !target.isAlive();
    }
}
