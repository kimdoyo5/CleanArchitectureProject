package main.java.entity;


import java.util.Map;

/**
 * Interface for the player entity that saves the specific data of the player
 * and provides a method to calculate all the stats needed
 */
public interface Player {

    /**
     * Gets the name of the player
     * @return name of Player
     */
    String getName();

    /**
     * gets the id of the player
     * @return id of player
     */
    int getID();

    /**
     * gets the stats of the player
     * @return The stat of the player
     */
    Map<String, String> getStats();

    /**
     * Calculates the target stat
     * @param target the target stat within "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS"
     * @return the calculated stat
     */
    String calculateState(String target);
}
