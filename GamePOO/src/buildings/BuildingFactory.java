<<<<<<< HEAD
package buildings;

import resources.ResourceManager;

public class BuildingFactory {

    public static Building createBuilding(String type, ResourceManager rm) {
        switch (type.toLowerCase()) {
            case "farm":
                return new Farm(rm);
            case "mine":
                return new Mine(rm);
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
=======
/*package buildings;

public class BuildingFactory {
    
    public static Building createBuilding(String type) {
        
        if (type.equalsIgnoreCase("farm")) {
            return new Farm();
        } 
        else if (type.equalsIgnoreCase("mine")) {
            return new Mine();
        } 
        else if (type.equalsIgnoreCase("barracks")) {
            return new Barracks();
        } 
        else {
            System.out.println("Type inconnu: " + type);
            return null;
        }
    }
}*/
>>>>>>> branch 'Hossem' of https://github.com/HOSSEM476/poo_project
