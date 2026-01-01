package buildings;

import GamePanelAndFrame.GamePanel;

public class BuildingFactory {

    public static Building createBuilding(String name, GamePanel g) {
        switch (name.toLowerCase()) {
            case "barracks": return new Barracks(g);
            case "archeryrange": return new ArcheryRange(g);
            case "stable": return new Stable(g);
            case "magetower": return new Magetower(g);
            default: return null;
        }
    }
}



