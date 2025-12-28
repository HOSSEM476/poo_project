package player;

public class TurnManager {
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public TurnManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isPlayer1Turn = true;
    }
	public void startGame() {
        getCurrentPlayer().startTurn();
    }

    public void nextTurn() {
        getCurrentPlayer().endTurn();
        isPlayer1Turn = !isPlayer1Turn;
        getCurrentPlayer().startTurn();
    }

    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }
}