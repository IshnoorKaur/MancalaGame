package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private int numKalahGPlayed;
    private int numAyoGamesPlayed;
    private int numKalahGamesWon;
    private int numAyoGamesWon;

    /**
     * Default constructor for UserProfile.
     */
    public UserProfile(){
        this("");
    }

    /**
     * Parameterized constructor for UserProfile.
     *
     * @param newName The name to set for the user profile.
     */
    public UserProfile(final String newName) {
        this.userName = newName;
        this.numKalahGPlayed = 0;
        this.numAyoGamesPlayed = 0;
        this.numKalahGamesWon = 0;
        this.numAyoGamesWon = 0;

    }

    /**
     * Gets the user's name.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user profile for a player.
     *
     * @param player The player whose profile will be set.
     */
    public void setUserProfile(final Player player) {
        player.setUserProfile(this);
    }

    /**
     * Sets the user's name.
     *
     * @param newName The new name for the user.
     */
    public void setName(final String newName){
        userName = newName;
    }

    /**
     * Gets the number of Kalah games played.
     *
     * @return The number of Kalah games played.
     */
    public int getNumKalahGamesPlayed() {
        return numKalahGPlayed;
    }

    /**
     * Increments the number of Kalah games played.
     */
    public void incrementNumKalahGamesPlayed() {
        numKalahGPlayed++;
    }

    /**
     * Gets the number of Ayoayo games played.
     *
     * @return The number of Ayoayo games played.
     */
    public int getNumAyoGamesPlayed() {
        return numAyoGamesPlayed;
    }

    /**
     * Increments the number of Ayoayo games played.
     */
    public void incrementNumAyoGamesPlayed() {
        numAyoGamesPlayed++;
    }

    /**
     * Gets the number of Kalah games won.
     *
     * @return The number of Kalah games won.
     */
    public int getNumKalahGamesWon() {
        return numKalahGamesWon;
    }

    /**
     * Increments the number of Kalah games won.
     */
    public void incrementNumKalahGamesWon() {
        numKalahGamesWon++;
    }

    /**
     * Gets the number of Ayoayo games won.
     *
     * @return The number of Ayoayo games won.
     */
    public int getNumAyoGamesWon() {
        return numAyoGamesWon;
    }

    /**
     * Increments the number of Ayoayo games won.
     */
    public void incrementNumAyoGamesWon() {
        numAyoGamesWon++;
    }
}

