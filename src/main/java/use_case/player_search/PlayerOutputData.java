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
    Map<String, String> mainKeys = new HashMap<>();

    Map<String, String> statKeys = new HashMap<>();

    /**
     * Initializes the class
     * @param player the player entity that contains the data of the player
     */
    public PlayerOutputData(Player player){
        this.player = player;
        String[] simple = {"hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg"};
        String[] fullName = {"Home Runs",
                "Totsl Bases",
                "Extra Base Hits",
                "Base on Balls",
                "Hits",
                "Caught Stealing",
                "Stolen Bases",
                "At Bats",
                "On Base Percentage",
                "Slugging Percentage" };
        for (int i = 0; i < simple.length; i++){
            mainKeys.put(simple[i], fullName[i]);
        }
        String[] stat_key = {"HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS"};
        String[] statName = {"Home Run Rate",
                "Caught Stealing Rate",
                "Hits plus Base on Balls Rate",
                "Hard Hit Rate",
                "On Base plus Slugging Percentage",
                "Weighted On Base plus Slugging Percentage"};
        for (int i = 0; i < stat_key.length; i++){
            statKeys.put(stat_key[i], statName[i]);
        }
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
     * Get the player
     * @return the player as a Player object
     */
    public Player getPlayer(){
        return player;
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
        for (String key: mainKeys.keySet()){
            data.put(mainKeys.get(key), player.getStats().get(key));
        }

        for (String key: statKeys.keySet()){
            data.put(statKeys.get(key), player.calculateState(key));
        }
        return data;
    }
}
