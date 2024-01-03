package mancala;

/**
 * Represents the rules of the Ayoayo variant of the Mancala game.
 */
public class AyoRules extends GameRules {

    private static final long serialVersionUID = 1L;

    private static final int NUM_ONE = 1;

    /**
     * Executes the Ayoayo move, where stones are moved based on the specified
     * starting pit and player.
     *
     * @param startPit  The pit from which the move begins.
     * @param playerNum The identifier of the player initiating the move.
     * @return The count of stones added to the player's store as a result of the
     *         move.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {

        if (!validMove(startPit, playerNum)) {
            throw new InvalidMoveException();
        }

        final int initialStore = stonesInStore(playerNum);
        distributeStones(startPit);

        final MancalaDataStructure gameBoard = getDataStructure();
        final int lastPit = getEndPit(gameBoard.positionOfIterator());

        final int finalStore = stonesInStore(playerNum);
        final int stolenStones = stealStonesAyoayo(lastPit, playerNum);

        final int playerChange = switchPlayer(playerNum);
        setPlayer(playerChange);

        return (finalStore - initialStore) + stolenStones;
    }

    /**
     * Implements the Ayoayo stone distribution logic.
     *
     * @param startPit The pit from which stone distribution begins.
     * @return The number of stones distributed.
     */
    @Override
    /* default */int distributeStones(final int startPit) {
        // In Ayoayo, the starting pit is excluded from distribution
        int stonesDistributed = 0;
        final int index = startPit;

        final MancalaDataStructure gameBoard = getDataStructure();
        Countable curspot;
        gameBoard.setIterator(startPit, getCurrentPlayer(), true);

        int stonesInPit = gameBoard.removeStones(index);

        stonesDistributed = stonesDistributed + stonesInPit;

        while (stonesInPit != 0) {
            curspot = gameBoard.next();
            curspot.addStone();
            stonesInPit--;

            // Additional check to avoid distributing stones to the opponent's store
            if (stonesInPit == 0 && curspot instanceof Pit && curspot.getStoneCount() != 1) {
                stonesInPit = curspot.removeStones();
                stonesDistributed = stonesDistributed + stonesInPit;
            }
        }
        return stonesDistributed;
    }

    /**
     * Implements the Ayoayo stone capture logic.
     *
     * @param stoppingPoint The point at which stone capture stops.
     * @return The count of stones captured during the process.
     */
    @Override
    /* default */int captureStones(final int stoppingPoint) {

        final int oppPit = getOppositePit(stoppingPoint);
        return removeStones(oppPit);
    }

    /* default */private int stealStonesAyoayo(final int endPit, final int playerNum) {
        int stolenStones = 0;

        if (isPlayerPit(playerNum, endPit) && getNumStones(endPit) == NUM_ONE) {
            stolenStones = captureStones(endPit);
            getDataStructure().addToStore(playerNum, stolenStones);
        }

        return stolenStones;
    }

    private boolean isPlayerPit(final int playerNum, final int pit) {
        return playerNum == 1 && pit >= 1 && pit <= 6 || playerNum == 2 && pit >= 7 && pit <= 12;
    }

    private int getEndPit(final int end) {
        int result;
        if (end >= 0 && end <= 5) {
            result = end + 1;
        } else if (end >= 7 && end <= 12) {
            result = end;
        } else {
            result = -1;
        }
        return result;
    }

}
