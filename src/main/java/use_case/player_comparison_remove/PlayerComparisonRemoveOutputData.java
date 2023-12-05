package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

public class PlayerComparisonRemoveOutputData {

    final private List<String> removedPlayers;

    public PlayerComparisonRemoveOutputData(List<String> removedPlayers){
        this.removedPlayers = removedPlayers;
    }

    public List<String> getRemovedPlayers(){
        return this.removedPlayers;
    }

}
