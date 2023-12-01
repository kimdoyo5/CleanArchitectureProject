package main.java.entity;

import org.json.JSONObject;

import main.java.entity.Player;


import java.util.List;
import java.util.Map;

/**
 * Creates the player entity based on the inputted information
 */
public interface PlayerFactory {

    /**
     * Creates the player object
     * @param info The info of the player
     * @param stats The basic statistics of the player
     * @return The player object that contains all the information needed
     */
    Player create(JSONObject info, JSONObject stats);

    Player create(List<String> playerInfo);
}
