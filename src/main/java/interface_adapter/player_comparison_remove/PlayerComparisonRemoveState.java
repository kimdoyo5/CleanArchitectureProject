package main.java.interface_adapter.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

/**
 * State for player comparison remove view model
 * contains a list of the names of the last removed player from the player comparison and any errors recorded with
 * player comparison remove
 */
public class PlayerComparisonRemoveState {

    private List<String> lastRemovedPlayers;

    private String playerRemoveError = null;

    /**
     * Constructor for the class
     */
    public PlayerComparisonRemoveState(){
    }

    /**
     * Sets the last removed players from the comparison
     */
    public void setLastRemovedPlayer(List<String> lastRemovedPlayers){
        this.lastRemovedPlayers = lastRemovedPlayers;
    }

    /**
     * Sets the error that occurred when attempting to remove all players from the comparison
     */
    public void setPlayerRemoveError(String playerRemoveError){
        this.playerRemoveError = playerRemoveError;
    }

    /**
     * Gets the last removed players from the comparison
     */
    public List<String> getLastRemovedPlayers(){
        return lastRemovedPlayers;
    }

    /**
     * Gets the error that occurred when attempting to remove all players from the comparison
     */
    public String getPlayerRemoveError(){
        return playerRemoveError;
    }


}
