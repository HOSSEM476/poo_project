package units;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Unit {

    // Stats
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int cost;

    protected Random random = new Random();

    // Drawing-related variables
    public BufferedImage image;
    public int x, y;
    public int tileSize;

    // Constructor
    public Unit(String name, int hp, int attack, int defense, int cost) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
    }

    // Drawing setup
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    // Abstract methods
    public abstract void attack(Unit target);
    public abstract String getType();

    // Gameplay methods
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Getters
    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

   
}

