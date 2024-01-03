package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private static final long serialVersionUID = 1L;

    final private MancalaDataStructure gameBoard;
    private static final int NUM_ONE = 1;
    private int currentPlayer = 1; // Player # 1/2

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        resetBoard();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /* default */int stonesInStore (final int playerNum){
        return gameBoard.getStoreCount(playerNum);
    }

    /* default */ int getWinner(){
        for(int i = 0; i < 7; i++){
            final int totalStones = gameBoard.removeStones(i);
            gameBoard.addStones(1, totalStones);
        }

        for(int i = 7; i < 13; i++){
            final int totalStones = gameBoard.removeStones(i);
            gameBoard.addStones(2, totalStones);
        }

        if(gameBoard.getStoreCount(1) == gameBoard.getStoreCount(2)){
            return 0;
        } else if(gameBoard.getStoreCount(1) > gameBoard.getStoreCount(2)){
            return 1;
        } else if (gameBoard.getStoreCount(1) < gameBoard.getStoreCount(2)){
            return 2;
        }
        return 0; 
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    /* default */MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */boolean isSideEmpty(final int pitNum) {
        // This method can be implemented in the abstract class.
        int startPit; 
        int endPit;

        if(pitNum >= 1 && pitNum <= 6){
            startPit = 1;
            endPit = 6;
        } else if(pitNum >= 7 && pitNum <= 12){
            startPit = 7;
            endPit = 12;
        }else{
            //Invalid pit num
            return false;
        }

        // Check if all pits in the range are empty
        for (int i = startPit; i <= endPit; i++) {
            if (getNumStones(i) > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    /* default */public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.


        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
        
         final Store storeOne = new Store();
         storeOne.setOwner(one);
         one.setStore(storeOne);
         gameBoard.setStore(storeOne, 1);

         final Store storeTwo = new Store();
         storeTwo.setOwner(two);
         two.setStore(storeTwo);
         gameBoard.setStore(storeTwo, 2);
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    /* default */ int getCurrentPlayer(){
        return currentPlayer;
    }

    /* default */ int getOppositePit(final int pitNum) {
        return (12 - pitNum) + 1;
    }

    /* default */int removeStones(final int pitNum) {
        return getDataStructure().removeStones(pitNum);
    }

    /* default */int switchPlayer(final int playerNum) {
        if (playerNum == NUM_ONE) {
            return 2;
        } else {
            return 1;
        }
    }

    /* default */ boolean validMove(final int startPit, final int playerNum){
        if(playerNum == 1 && (startPit < 1 || startPit > 6)){
            return false; 
        }
        if(playerNum == 2 && (startPit < 7 || startPit > 12)){ 
            return false;
        }
        if(getNumStones(startPit) == 0){
            return false;
        }

        return true;
    }

    public int getCurrent(){
        return currentPlayer;
    }
    @Override
    public String toString() {
        // Implement toString() method logic here.
            // Display the current state of the board
    final StringBuilder boardString = new StringBuilder("Mancala Board:\n");

    // Display Player 2 pits
    boardString.append("Player 2:  ");
    for (int i = 12; i > 6; i--) {
        boardString.append("[");
        boardString.append(gameBoard.getNumStones(i));
        boardString.append("] ");
    }
    boardString.append("\n");
    boardString.append("Player 2 Store: ");
    boardString.append(gameBoard.getStoreCount(2));
    boardString.append("\n");

    // Display Player 1 pits
    boardString.append("Player 1:  ");
    for (int i = 1; i < 7; i++) {
        boardString.append("[");
        boardString.append(gameBoard.getNumStones(i));
        boardString.append("] ");
    }
    boardString.append("\n");
    boardString.append("Player 1 Store: ");
    boardString.append(gameBoard.getStoreCount(1));

    return boardString.toString();
    }
}
