package main.java.use_case.player_search;

import main.java.entity.Player;

import java.io.IOException;

/**
 * An object used to access data outside the program
 * contains method search that searches for the target player and returns it as a player entity
 */
public interface PlayerSearchDataAccessInterface {
    /**
     * Searches for the player based on the id provided
     * @param player_id the id of the player you want to search
     * @return the player object that contains the info found about that player
     * @throws IOException error when searching or forming the data
     */
    Player search(int player_id) throws IOException;

}
