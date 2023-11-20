package main.java.entity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommonPlayerFactory implements PlayerFactory{
    String[] keys = {"hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg"};

    @Override
    public Player create(JSONObject info, JSONObject stats) {
        Map<String, String> stat = new HashMap<>();
        for(String key: keys){
            stat.put(key, stats.getString("key"));
        }
        return new CommonPlayer(info.getString("name_display_first_last"), info.getInt("player_id"), stat);
    }
}
