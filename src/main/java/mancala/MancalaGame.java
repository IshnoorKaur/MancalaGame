package mancala;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Mancala game, which includes the game board and players.
 */
public class MancalaGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int NUM_ONE = 1;
    private static final int NUM_TWO = 2;

    private GameRules gameRules;
    private Player currentPlayer;
    final private ArrayList<Player> players;

    /**
     * Initializes a new Mancala game.
     */
    public MancalaGame() {
        players = new ArrayList<>();

    }

    /**
     * Sets the players for the game.
     *
     * @param onePlayer The first player.
     * @param twoPlayer The second player.
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        players.add(onePlayer);
        players.add(twoPlayer);
        gameRules.registerPlayers(onePlayer, twoPlayer);
        setCurrentPlayer(onePlayer);

    }

    /**
     * Gets the players for the game.
     *
     * @return ArrayList of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player.
     *
     * @param player The player to set as the current player.
     */
    public void setCurrentPlayer(final Player player) {
        currentPlayer = player;
    }

    /**
     * Sets the board for the game.
     *
     * @param theBoard The game board.
     */
    public void setBoard(final GameRules theBoard) {
        // boardGame = theBoard;
        gameRules = theBoard;
    }

    /**
     * Gets the board for the game.
     *
     * @return The game board.
     */
    public GameRules getBoard() {
        return gameRules;
    }

    /**
     * Gets the number of stones in a specific pit.
     *
     * @param pitNum The pit number.
     * @return The number of stones in the pit.
     * @throws PitNotFoundException If the pit number is invalid.
     */
    public int getNumStones(final int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException();
        }
        return gameRules.getNumStones(pitNum);
    }

    /**
     * Gets the number of stones in a pit 
     * @param pitNum The pit number.
     * @return The number of stones in the pit.
     */
    /*Default*/public int stonesInPit(final int pitNum){
        return gameRules.getNumStones(pitNum);
    } 
    /**
     * Makes a move for the current player.
     *
     * @param startPit The starting pit for the move.
     * @return The player number.
     * @throws InvalidMoveException If the move is invalid.
     */
    public int move(final int startPit) throws InvalidMoveException {
        int playerNumber;

        if (getCurrentPlayer() == getPlayers().get(0)) {
            playerNumber = 1;
        } else {
            playerNumber = 2;
        }

        gameRules.moveStones(startPit, playerNumber);

        switchPlayer();
        return playerNumber;
    }

    /**
     * Switches the current player.
     */
    private void switchPlayer() {
        if (gameRules.getCurrentPlayer() == NUM_ONE) {
            setCurrentPlayer(getPlayers().get(0));
        } else {
            setCurrentPlayer(getPlayers().get(1));
        }
    }

    /**
     * Gets the total number of stones in a player's store.
     *
     * @param player The player.
     * @return The total number of stones in the player's store.
     * @throws NoSuchPlayerException If the player is not in the game.
     */
    public int getStoreCount(final Player player) throws NoSuchPlayerException {

        if (player != players.get(0) && player != players.get(1)) {
            throw new NoSuchPlayerException();
        }

        return player.getStoreCount();
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameRules.isSideEmpty(1) || gameRules.isSideEmpty(7);

    }

    /**
     * Gets the winner of the game.
     *
     * @return The winner player.
     * @throws GameNotOverException If the game is not over.
     */
    public Player getWinner() throws GameNotOverException {

        if (isGameOver()) {
            final int gameWinner = gameRules.getWinner();

            if (gameWinner == NUM_ONE) {
                return players.get(0);
            }
            if (gameWinner == NUM_TWO) {
                return players.get(1);
            }

            return null;
        } else {
            throw new GameNotOverException();
        }

    }

    /**
     * Starts a new game by resetting the board.
     */
    public void startNewGame() {
        gameRules.resetBoard();
        currentPlayer = players.get(0);

    }

    /**
     * Starts a new game according to Kalah rules.
     */
    public void boardKalahGame() {
        gameRules = new KalahRules();
    }

    /**
     * Sets the board for Ayoayo.
     */
    public void boardAyoayoGame() {
        gameRules = new AyoRules();
    }

    @Override
    public String toString() {
        // Generate a string representation of the game and board
        final StringBuilder gameString = new StringBuilder("Current Player: ");
        gameString.append(currentPlayer.getName());
        gameString.append("\n");

        // Display the board
        gameString.append(gameRules.toString());

        return gameString.toString();
    }
}
