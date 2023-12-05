package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

/**
 * Interface used to access the data needed for the player comparison remove use case
 */
public interface PlayerComparisonRemoveDataAccessInterface {

    /**
     * Removes all players from the player comparison lists
     * @return a list of strings containing the names of the removed players
     */
    List<String> removedPlayers();

    /**
     * Gets the amount of players in the comparison
     * @return an int describing the amount of players in the comparison
     */
    int getSize();

    /**
     * Attempts to add the given player to the comparison and returns whether the add was successful\
     * This exists solely for test purposes
     * @param player the player being added to the comparison
     * @return boolean on if the player was successfully added to the comparison
     */
    boolean add(Player player);

}
