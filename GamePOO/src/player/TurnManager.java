package player;

import java.util.List;

public class TurnManager {
    private List<Player> players;
    private int currentPlayerIndex;

    public TurnManager(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public void startGame() {
        players.get(currentPlayerIndex).startTurn();
    }

    public void nextTurn() {
        players.get(currentPlayerIndex).endTurn();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        players.get(currentPlayerIndex).startTurn();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
}