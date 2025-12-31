<<<<<<< HEAD
package units;

import units.Unit;

public class CombatSystem {

    public static boolean resolveAttack(Unit attacker, Unit target) {
     
        attacker.attack(target);

        return !target.isAlive();
    }
}
=======
package units;

import units.Unit;

public class CombatSystem {

    public static boolean resolveAttack(Unit attacker, Unit target) {
     
        attacker.attack(target);

        return !target.isAlive();
    }
}
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
