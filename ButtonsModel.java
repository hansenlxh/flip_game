
import java.util.ArrayList;
import java.util.List;





public class ButtonsModel {
    boolean easy = true;
    int n = 4;
    boolean [][] boardStates;
	int currentValue;
    boolean aiTurnBeginNotif;
    int aitarget = 0;
    boolean finalMove;

    final double leftBound = 0;
	final double rightBound = 600;
	final double topBound = 0;
	final double bottomBound = 600;

    private int gameState;

	public ButtonsModel() {
        initializeStates();
        //initialize why not
	}

    public void initializeStates() {
        aiTurnBeginNotif = false;
        finalMove = false;
        currentValue = 0;
        gameState = 0;
        n = easy ? 4:8;
        boardStates = new boolean [n][n];
		for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                boardStates[i][j]= false;
            }
        }

    }
	public void flipSquare(int number) {
        currentValue = currentValue ^ number;
        int row = number / n;
        int column = number - row * n;
		boardStates[row][column] = !boardStates[row][column];
	}
	public int returnTargetFlip(int target) {
		return target ^ currentValue;
	}
    public void incrementGameState(){
        gameState ++;
    }

    public void setGameState(int num) {
        //key code:

        //0-toggle between 4x4 and 8x8
        //1-create and set initial arrangement
        //2-pick who goes first
        //3- you pick first
        //5 - final state
        gameState = num;
    }
    public int getGameState(){
        return gameState;
    }
	
}
