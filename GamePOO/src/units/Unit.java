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

<<<<<<< HEAD
    public abstract String getType();
    public int getCost() {
    	return cost;
=======
    // Getters
    public int getCost() {
        return cost;
>>>>>>> branch 'Aymen' of https://github.com/HOSSEM476/poo_project
    }

<<<<<<< HEAD
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

	public Random getRandom() {
		return random;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setRandom(Random random) {
		this.random = random;
	}
=======
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

   
>>>>>>> branch 'Aymen' of https://github.com/HOSSEM476/poo_project
}
