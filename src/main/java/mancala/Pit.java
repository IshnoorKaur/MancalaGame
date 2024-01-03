package mancala;

import java.io.Serializable;

/**
 * Represents a pit in the Mancala game that can hold stones.
 */
public class Pit implements Countable, Serializable {
    private static final long serialVersionUID = 1L;

    private int stoneCount;

    /**
     * Default constructor. Initializes the pit with zero stones.
     */
    public Pit() {
        stoneCount = 0;
    }

    /**
     * Adds a single stone to the pit.
     */
    @Override
    public void addStone() {
        stoneCount++;
    }

    /**
     * Adds a specified amount of stones to the pit.
     *
     * @param amount The number of stones to add to the pit.
     */
    @Override
    public void addStones(final int amount) {
        stoneCount = stoneCount + amount;
    }

    /**
     * Gets the count of stones in the pit.
     *
     * @return The count of stones in the pit.
     */
    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Removes all stones from the pit and returns the
     * count of removed stones.
     *
     * @return The count of stones removed from the pit.
     */
    @Override
    public int removeStones() {
        final int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }

    @Override
    public String toString() {
        return "Pit has " + getStoneCount() + " stones.";
    }
}
