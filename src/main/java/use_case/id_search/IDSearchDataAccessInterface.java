package main.java.use_case.id_search;

import java.util.List;
import java.util.Map;

public interface IDSearchDataAccessInterface {
    /**
     * Checks if a player exists in the database with the given id
     * @param id the ID to check
     * @return true if the player exists in the database, false otherwise
     */
    boolean isPlayer(int id);

    /**
     * Checks if a player exists in the database with a given name
     * @param name the name to check
     * @return true if the player exists in the database, false otherwise
     */
    boolean isPlayer(String name);

    /**
     * Gets the ID of a player with a given name
     * @param name the name of the player to search for
     * @return a map containing all the matching players as keys mapped to
     * their corresponding IDs.
     */
    Map<String, Integer> getID(String name);
}
