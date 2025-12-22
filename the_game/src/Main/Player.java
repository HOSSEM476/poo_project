package Main;

public class Player {
    private int x;
    private int y;
    private GameMap gameMap;

    public Player(int startX, int startY, GameMap gameMap) {
        this.x = startX;
        this.y = startY;
        this.gameMap = gameMap;
    }

    public void moveUp() {
        if (gameMap.isWalkable(x, y - 1)) {
            y--;
        }
    }

    public void moveDown() {
        if (gameMap.isWalkable(x, y + 1)) {
            y++;
        }
    }

    public void moveLeft() {
        if (gameMap.isWalkable(x - 1, y)) {
            x--;
        }
    }

    public void moveRight() {
        if (gameMap.isWalkable(x + 1, y)) {
            x++;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        if (gameMap.isWalkable(x, y)) {
            this.x = x;
            this.y = y;
        }
    }
}
