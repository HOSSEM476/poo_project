package Main;

public class GameMap {
    private int[][] tiles;
    private int width;
    private int height;
    private static final int TILE_SIZE = 32;
    
    // Tile types
    public static final int GRASS = 0;
    public static final int WATER = 1;
    public static final int TREE = 2;
    public static final int STONE = 3;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[height][width];
        generateMap();
    }

    private void generateMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = GRASS;
            }
        }
        
        // Add some water
        for (int y = 5; y < 8; y++) {
            for (int x = 5; x < 10; x++) {
                tiles[y][x] = WATER;
            }
        }
        
        // Add some trees
        tiles[2][3] = TREE;
        tiles[2][4] = TREE;
        tiles[3][3] = TREE;
        tiles[10][10] = TREE;
        tiles[11][10] = TREE;
        tiles[15][5] = TREE;
    }

    public int getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return -1;
        }
        return tiles[y][x];
    }

    public boolean isWalkable(int x, int y) {
        int tile = getTile(x, y);
        return tile == GRASS || tile == STONE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int[][] getTiles() {
        return tiles;
    }
}