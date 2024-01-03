package mancala;

/**
 * Represents the rules of the Kalah variant of the Mancala game.
 */
public class KalahRules extends GameRules {

    private static final long serialVersionUID = 1L;
    private static final int NUM_ONE = 1;


    /**
     * Implements the Kalah move logic.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {

        if (!validMove(startPit, playerNum)) {
            throw new InvalidMoveException();
        }
        final int initailStore = stonesInStore(playerNum);

        final int stonesDistributed = distributeStones(startPit);

        final int lastPit = (startPit + stonesDistributed) % 13;

        final int finalStore = stonesInStore(playerNum);

        final int stolenStones = stealStones(lastPit, playerNum);

        if (finalStore == initailStore + 1 && playerNum == 1 && lastPit == 7) {
            setPlayer(playerNum);
        } else if (finalStore == initailStore + 1 && playerNum == 2 && lastPit == 0) {
            setPlayer(playerNum);
        } else {
            final int playerChange = switchPlayer(playerNum);
            setPlayer(playerChange);
        }

        return (finalStore - initailStore) + stolenStones ;

    }

    /**
     * Implements the Kalah stone distribution logic.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    @Override
    /* default */int distributeStones(final int startPit) {

        final int stonesInPit = removeStones(startPit);

        final MancalaDataStructure gameBoard = getDataStructure();

        gameBoard.setIterator(startPit, getCurrentPlayer(), false);
        Countable curSpot;

        for (int i = 0; i < stonesInPit; i++) {
            curSpot = gameBoard.next();
            curSpot.addStone();
        }
        return stonesInPit;
    }

    private int stealStones( final int endPit, final int playerNum) {
        
        int stolenStones = 0;

        if (isPlayerPit(playerNum, endPit) && getNumStones(endPit) == NUM_ONE) {
            stolenStones = captureStones(endPit);
            getDataStructure().addToStore(playerNum, stolenStones);
        }

        return stolenStones;
    }

    /**
     * Implements the Kalah stone capture logic.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    @Override
    /* default */int captureStones(final int stoppingPoint) {
        final int oppPit = getOppositePit(stoppingPoint);
        return removeStones(oppPit) + removeStones(stoppingPoint);
    }

    /* default */private boolean isPlayerPit(final int playerNum, final int pit) {
        return playerNum == 1 && pit >= 1 && pit <= 6 || playerNum == 2 && pit >= 7 && pit <= 12;
    }

}
