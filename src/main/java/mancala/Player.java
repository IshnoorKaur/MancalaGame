package mancala;

import java.io.Serializable;

/**
 * Represents a player in the Mancala game.
 * Each player has a name, a user profile, and a store for collecting stones.
 */

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nameOfPlayer;
    private UserProfile userProfile;
    private Store storeOfPlayer;

    /**
     * Default constructor. Creates a player with an empty name.
     */
    public Player() {
        this("");
    }

    /**
     * Gets the user profile associated with the player.
     *
     * @return The user profile of the player.
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the user profile for the player.
     *
     * @param userProfile The user profile to be set.
     */
    public void setUserProfile(final UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Constructs a player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(final String name) {
        nameOfPlayer = name;
        userProfile = new UserProfile(name);
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name to be set for the player.
     */
    public void setName(final String name) {
        nameOfPlayer = name;
    }

    /**
     * Sets the store for the player.
     *
     * @param store The store to be set for the player.
     */
    public void setStore(final Store store) {
        storeOfPlayer = store;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return nameOfPlayer;
    }

    /**
     * Gets the store of the player where they collect stones.
     *
     * @return The store of the player.
     */
    public Store getStore() {
        return storeOfPlayer;
    }

    /**
     * Gets the count of the number of stones in the player's store.
     *
     * @return The count of stones in the player's store.
     */
    public int getStoreCount() {
        return storeOfPlayer.getStoneCount();
    }

    @Override
    public String toString() {
        return "Player Name: " + getName() + " \n " + "Stones in store: " + getStoreCount();
    }

}
