package main.java.use_case.player_search;

import main.java.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerOutputData {
    Player player;
    public PlayerOutputData(Player player){
        this.player = player;
    }

    public String getName(){
        return player.getName();
    }
    public int getID(){
        return player.getID();
    }

    public Map<String, String> getStats(){
        return player.getStats();
    }

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
