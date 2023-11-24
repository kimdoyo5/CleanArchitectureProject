package main.java.interface_adapter.player_comparison_add;

import main.java.entity.Player;

import java.util.ArrayList;

public class PlayerComparisonAddState {

    private ArrayList<Player> players = new ArrayList<Player>();

    private String playerAddError = null;

    public PlayerComparisonAddState(){
    }

    public void setPlayers(ArrayList<Player> players){
        this.players = players;
    }

    public void setPlayerAddError(String playerAddError){
        this.playerAddError = playerAddError;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public String getPlayerAddError(){
        return playerAddError;
    }

}
