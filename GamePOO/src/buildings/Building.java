package buildings;

public abstract class Building {
    
    protected String name;
    protected static int cost;
    
    public Building(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    
    public String getName() {
        return name;
    }
    
    public static int getCost() {
        return cost;
    }
    
    // methode abstraite
    public abstract void produce();
    
    public String toString() {
        return name + " (cout: " + cost + ")";
    }

}
