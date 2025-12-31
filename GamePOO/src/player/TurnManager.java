package player;
import units.*;
import java.util.Random;
import java.util.random.RandomGenerator;

import GamePanelAndFrame.GamePanel;
import resources.ResourceManager;
import resources.ResourceType;


public class TurnManager {
	ResourceManager R;
	GamePanel g;
    public Player player1; // human
    public Player player2; // AI
    private boolean isPlayer1Turn; // whose turn
    private Random random = new Random();
    private boolean isAwaitingAction = true;
    private boolean awaitingUnitChoice = false;
	private boolean awaitingBuildingChoice = false;
    
    public TurnManager(Player player1, Player player2, GamePanel g) {
        this.player1 = player1;
        this.player2 = player2;
        this.isPlayer1Turn = true;
        this.g = g;
    }

	public void startGame() {
        getCurrentPlayer().startTurn();
    }

    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    // Start current player's turn
    public void startTurn() {
        Player current = getCurrentPlayer();
        current.showUnits();
        
        setAwaitingAction(true);

            if (current.isAi()) {
                aiAct(current);
                nextTurn();
            } else {
                g.addCommentary("Choose an action:");
                g.addCommentary("1 = Attack, 2 = Build, 3 = Train Units");
            }
        }

    // End current turn and switch to the other player
    public void nextTurn() {
        Player current = getCurrentPlayer();
        current.addGold(10);
        current.addFood(10);
        if (player1.getG() != null) {
            player1.getG().clearCommentary(); // clears previous turn text
        }
        current.endTurn();
        isPlayer1Turn = !isPlayer1Turn;

        // Start next player's turn
        startTurn();
    }

    // Human trains a unit (choice 1-4)
    public void humanTrainUnit(int choice) {
        Player human = getCurrentPlayer();
        if (!human.isAi()) {
            Unit unit = createUnitByChoice(choice);
            if (unit != null && human.getFood() >= unit.getCost()) {
                human.trainUnit(unit);
                human.spendFood(unit.getCost());
            } else if (unit != null) {
                if (human.getResourceManager() != null)
                    human.getResourceManager().getResource(ResourceType.GOLD); // optional commentary
            }
        }
    }

    // AI performs one action per turn
    private void aiAct(Player ai) {
        int choice = random.nextInt(3); // 0=collect, 1=train, 2=attack

        switch (choice) {
            case 0:
                // Resources already collected at start of turn
                break;

            case 1:
                Unit unit = createUnitByChoice(random.nextInt(4) + 1);
                if (unit != null && ai.getGold() >= unit.getCost()) {
                    ai.trainUnit(unit);
                    ai.spendGold(unit.getCost());
                }
                break;

            case 2:
                // Attack human unit if exists
                Player human = player1;
                int rand = random.nextInt(5);
                if (!human.getUnits().isEmpty()) {
                    Unit target = human.getUnits().get(rand); // pick first unit
                    ai.onEnemyUnitKilled(target);
                    human.getUnits().remove(target);
                }
                break;
        }
    }

    // Factory method to create units
    public static Unit createUnitByChoice(int choice) {
        switch (choice) {
            case 1: return new Soldier();
            case 2: return new Archer();
            case 3: return new Cavalry();
            case 4: return new Wizard();
            default: return null;
        }
    }

	public boolean isAwaitingAction() {
		return isAwaitingAction;
	}

	public void setAwaitingAction(boolean isAwaitingAction) {
		this.isAwaitingAction = isAwaitingAction;
	}

	public boolean isAwaitingUnitChoice() {
		return awaitingUnitChoice;
	}

	public void setAwaitingUnitChoice(boolean awaitingUnitChoice) {
		this.awaitingUnitChoice = awaitingUnitChoice;
	}

	public void setAwaitingBuildingChoice(boolean b) {
		this.awaitingBuildingChoice  = b;		
	}

	public boolean isAwaitingBuildingChoice() {
		return awaitingBuildingChoice;
	}
}
