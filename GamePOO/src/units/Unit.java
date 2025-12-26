package units;

import java.util.Random;

public abstract class Unit {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int cost;

    protected Random random = new Random();

    public Unit(String name, int hp, int attack, int defense, int cost) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
    }

    public abstract void attack(Unit target);

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
    public int getDefense() { return defense; }
}
