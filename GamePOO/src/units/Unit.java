package units;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Unit {

    // ✅ Stats
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int cost;

    protected Random random = new Random();

    // ✅ Drawing-related variables
    protected BufferedImage image;  // the unit’s image
    protected int x, y;             // position in pixels
    protected int tileSize;         // size to draw the unit

    // ✅ Constructor for stats
    public Unit(String name, int hp, int attack, int defense, int cost) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
    }

    // ✅ Optional: set drawing info
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

    // ✅ Abstract methods for subclasses
    public abstract void attack(Unit target);
    public abstract String getType();

    // ✅ Stats methods
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

    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getDefense() { return defense; }

    // ✅ Drawing method
    public void draw(Graphics2D g2) {
        if (!isAlive()) return;      // only draw alive units
        if (image != null) {         // check image exists
            g2.drawImage(image, x, y, tileSize, tileSize, null);
        }
    }
}

