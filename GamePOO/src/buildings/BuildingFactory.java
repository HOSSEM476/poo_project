
package buildings;
public class BuildingFactory {
    public static Building createBuilding(String type) {
        switch (type.toLowerCase()) {
            case "barracks":            	
                return new Barracks();
            case "stable":
                return new Stable();
            case "archeryrange":
                return new ArcheryRange();
            case "magetower":
                return new Magetower();
            default:
                throw new IllegalArgumentException("Unknown building type: " + type);
        }
    }
}



