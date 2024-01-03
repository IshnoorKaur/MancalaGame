package mancala;

import java.io.Serializable;

/**
 * Represents a store in the Mancala game where a player collects stones.
 */
public class Store implements Countable, Serializable{
    private static final long serialVersionUID = 1L;

    private Player owner;
    private int totalStones;

    /**
     * Default constructor. Initializes the store with zero stones.
     */
    public Store() {
        totalStones = 0;
    }

    /**
     * Adds a specified amount of stones to the store.
     *
     * @param amount The number of stones to add to the store.
     */
    @Override
    public void addStones(final int amount) {
        totalStones += amount;
    }

    /**
     * Adds a single stone to the store.
     */
    @Override
    public void addStone(){
        totalStones++;
    }

    /**
     * Removes all stones from the store and returns the 
     * count of removed stones.
     *
     * @return The count of stones removed from the store.
     */
    @Override
    public int removeStones() {
        final int remStones = totalStones;
        totalStones = 0;
        return remStones;
    }

    /**
     * Gets the owner player of the store.
     *
     * @return The player who owns the store.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gets the count of stones in the store.
     *
     * @return The count of stones in the store.
     */
    @Override
    public int getStoneCount() {
        return totalStones;
    }

    /**
     * Sets the owner player of the store.
     *
     * @param player The player to set as the owner of the store.
     */
    public void setOwner(final Player player) {
        owner = player;
    }

    @Override
    public String toString() {
        return owner.getName() + " with " + totalStones + " stones.";
    }
}

