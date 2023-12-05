package main.java.interface_adapter.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

public class PlayerComparisonRemoveState {

    private List<String> lastRemovedPlayers;

    private String playerRemoveError = null;

    public PlayerComparisonRemoveState(){
    }

    public void setLastRemovedPlayer(List<String> lastRemovedPlayers){
        this.lastRemovedPlayers = lastRemovedPlayers;
    }

    public void setPlayerRemoveError(String playerRemoveError){
        this.playerRemoveError = playerRemoveError;
    }

    public List<String> getLastRemovedPlayers(){
        return lastRemovedPlayers;
    }

    public String getPlayerRemoveError(){
        return playerRemoveError;
    }


}
