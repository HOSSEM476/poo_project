package resources;
import java.util.EnumMap;

public class ResourceManager {
    private EnumMap<ResourceType, Integer> resources;

    public ResourceManager() {
        resources = new EnumMap<>(ResourceType.class);
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0); 
        }
    }

    public void addResource(ResourceType type, int amount) {
        resources.put(type, resources.get(type) + amount);
    }

    public boolean spendResource(ResourceType type, int amount) {
        int current = resources.get(type);
        if (current >= amount) {
            resources.put(type, current - amount);
            return true;
        }
        return false;
    }

    public int getResource(ResourceType type) {
        return resources.get(type);
    }

    public void printResources() {
        for (ResourceType type : ResourceType.values()) {
            System.out.println(type + ": " + resources.get(type));
        }
    }
}