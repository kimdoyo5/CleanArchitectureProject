package main.java.data_access;

import main.java.entity.Player;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestPlayerAddRemoveDataAccessObject implements PlayerComparisonAddDataAccessInterface, PlayerComparisonRemoveDataAccessInterface {

    private final Map<String, Player> players = new HashMap<>();

    public boolean add(Player player) {
        int playerId = player.getID();
        boolean alreadyIn = false;
        for (String key: players.keySet()){
            if (players.get(key).getID() == playerId){
                alreadyIn = true;
            }
        }

        if (players.size() >= 4 || alreadyIn) {
            return false;
        } else {
            players.put(player.getName(), player);
            return true;
        }
    }

    public int getSize(){
        return players.size();
    }

    public Player remove(int playerId){
        Set<String> keys = players.keySet();
        Player player = null;
        for(String key: keys){
            if (players.get(key).getID() == playerId){
                player = players.get(key);
                players.remove(key);
            }
        }
        return player;
    }

}
