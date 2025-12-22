package Main;

public class Game {
    private GameMap gameMap;
    private Player player;
    private MyFrame gameWindow;

    public Game() {
        initialize();
    }

    private void initialize() {
        // Initialize the game map
        this.gameMap = new GameMap(24, 18);
        System.out.println("Map created: " + gameMap.getWidth() + "x" + gameMap.getHeight() + " tiles");

        // Initialize the player
        this.player = new Player(5, 5, gameMap);
        System.out.println("Player initialized at position: (" + player.getX() + ", " + player.getY() + ")");

        // Create the game window
        this.gameWindow = new MyFrame();
        System.out.println("Game window created");
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Player getPlayer() {
        return player;
    }

    public MyFrame getGameWindow() {
        return gameWindow;
    }

    public void start() {
        System.out.println("Game started!");
        System.out.println("Controls: Arrow keys or WASD to move");
        System.out.println("Green = Grass (walkable)");
        System.out.println("Blue = Water (blocked)");
        System.out.println("Brown = Tree (blocked)");
    }
}
