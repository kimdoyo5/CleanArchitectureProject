package main.java.use_case.player_search;

import main.java.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * The data that the player search use case outputs
 * contains the player entity that the user searched for
 */
public class PlayerOutputData {
    Player player;

    /**
     * Initializes the class
     * @param player the player entity that contains the data of the player
     */
    public PlayerOutputData(Player player){
        this.player = player;
    }

    /**
     * Get the name of the player
     * @return the name of the player as String
     */
    public String getName(){
        return player.getName();
    }

    /**
     * Get the id of the player
     * @return the id of the player as integer
     */
    public int getID(){
        return player.getID();
    }

    /**
     * Gets the stats of the player
     * @return the stats of the player as a map
     */
    public Map<String, String> getStats(){
        return player.getStats();
    }

    /**
     * Forms the complete data set of the player
     * @return All needed info of the player as a map
     */
    public Map<String, String> getData(){
        Map<String, String> data = new HashMap<>();
        data.put("Name", player.getName());
        data.put("Player Id", String.valueOf(player.getID()));
        String[] stat_key = {"HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS"};
        for (String key: player.getStats().keySet()){
            data.put(key, player.getStats().get(key));
        }

        for (String key: stat_key){
            data.put(key, player.calculateState(key));
        }
        return data;
    }
}
