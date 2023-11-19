package use_case.player_search;

import entity.Player;

import java.util.Map;

public class PlayerOutputData {
    Player player;
    public PlayerOutputData(Player player){
        this.player = player;
    }

    String getName(){
        return player.getName();
    }
    int getID(){
        return player.getID();
    }

    Map<String, String> getStats(){
        return player.getStats();
    }
}
