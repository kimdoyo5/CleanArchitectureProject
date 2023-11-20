package main.java.use_case.player_search;

import main.java.entity.Player;

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
}
