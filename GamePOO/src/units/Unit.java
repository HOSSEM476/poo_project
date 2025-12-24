package units;

import java.util.Random;

public abstract class Unit {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int cost;

    protected Random random = new Random();

    public Unit(String name, int hp, int attack, int cost) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.cost = cost;
    }

    // Default attack behavior (for combat units)
    public void attack(Unit target) {
        int randomFactor = random.nextInt(3) + 1;
        int damage = this.attack * randomFactor;

        System.out.println(
            this.name + " attacks " + target.name +
            " for " + damage + " damage."
        );

        target.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public abstract String getType();

    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}