package main.java.entity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory for the player object
 */
public class CommonPlayerFactory implements PlayerFactory{
    final String[] keys = {"hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg"};

    @Override
    public Player create(JSONObject info, JSONObject stats) {
        Map<String, String> stat = new HashMap<>();
        for(String key: keys){
            stat.put(key, stats.getString(key));
        }
        return new CommonPlayer(info.getString("name_display_first_last"), info.getInt("player_id"), stat);
    }

    public Player create(List<String> playerInfo){
        String[] keys = {"hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg"};
        String playerName = playerInfo.get(0);
        int playerId = Integer.parseInt(playerInfo.get(1));
        Map<String, String> stats = new HashMap<>();

        for(int i = 0; i < keys.length; i++){
            stats.put(keys[i], playerInfo.get(i+2));
        }

        return new CommonPlayer(playerName, playerId, stats);

    }
}
