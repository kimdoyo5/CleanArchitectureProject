package main.java.interface_adapter.player_comparison_add;

import main.java.entity.Player;

import java.util.ArrayList;

public class PlayerComparisonAddState {

    private Player lastAddedPlayer;

    private String playerAddError = null;

    public PlayerComparisonAddState(){
    }

    public void setLastAddedPlayer(Player player){
        this.lastAddedPlayer = player;
    }

    public void setPlayerAddError(String playerAddError){
        this.playerAddError = playerAddError;
    }

    public Player getLastAddedPlayer(){
        return lastAddedPlayer;
    }

    public String getPlayerAddError(){
        return playerAddError;
    }

}
