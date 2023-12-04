package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

/**
 * Interface used to access the data needed for the player comparison add use case
 */
public interface PlayerComparisonAddDataAccessInterface {

    /**
     * Attempts to add the given player to the comparison and returns whether the add was successful
     * @param player the player being added to the comparison
     * @return boolean on if the player was successfully added to the comparison
     */
    boolean add(Player player);

    /**
     * Gets the amount of players in the comparison
     * @return an int describing the amount of players in the comparison
     */
    int getSize();

}
