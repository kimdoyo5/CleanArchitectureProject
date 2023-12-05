package main.java.data_access;

import main.java.entity.Player;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;

import java.util.*;

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

    public List<String> removedPlayers(){
        Set<String> keys = players.keySet();
        List<String> playerNames = new ArrayList<>();
        for(String key: keys){
            playerNames.add(key);
        }
        players.clear();
        return playerNames;

    }

}
