package Buildings;
import java.util.Map;

public abstract class Buildings {
	
    protected String name;
    protected int buildTime; // وقت البناء (بالأدوار)
    protected Map<String, Integer> cost;
    protected int remainingTime;
	protected boolean isBuilt;

    public Buildings(String name, int buildTime, Map<String, Integer> cost) {
        this.name = name;
        this.buildTime = buildTime;
        this.cost = cost;
        this.remainingTime = buildTime;
        this.isBuilt = false;

    }

    public abstract void use();

    public void showCost() {
        System.out.println("تكلفة " + name + ": " + cost);
        
    }
    public void build() {
        if (!isBuilt) {
            remainingTime--;
            System.out.println(name + " قيد البناء... الوقت المتبقي: " + remainingTime);

            if (remainingTime <= 0) {
                isBuilt = true;
                System.out.println(name + " تم بناؤه بنجاح!");
            }
        }
    }

}

