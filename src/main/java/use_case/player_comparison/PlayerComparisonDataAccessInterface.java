package main.java.use_case.player_comparison;

import main.java.entity.Player;

import java.util.List;
import java.util.Map;

/**
 * Interface for accessing player comparison data.
 * This interface provides methods for retrieving various types of data
 * related to player comparison, such as player statistics and leaders in different categories.
 */
public interface PlayerComparisonDataAccessInterface {

    /**
     * Gets the size or number of players added for comparison.
     *
     * @return the number of players added.
     */
    int getSize();

    /**
     * Retrieves the names of all players available for comparison.
     *
     * @return a list of all player names.
     */
    List<String> getAllPlayerNames();

    /**
     * Retrieves a simplified map of statistics for a given player.
     * The map includes key statistics identified by a string key.
     *
     * @param name the name of the player.
     * @return a map of player statistics with keys as stat names and values as stat values.
     */
    Map<String, Double> getSimplified(String name);

    /**
     * Retrieves a map of the leaders in different statistical categories.
     * The map includes the names of players leading in each category.
     *
     * @return a map with keys as stat names and values as names of leading players.
     */
    Map<String, String> getLeaders();

    boolean add(Player player);

}

