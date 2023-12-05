package main.java.interface_adapter.player_comparison_add;

import main.java.entity.Player;

import java.util.ArrayList;

/**
 * State for player comparison add view model
 * contains the last added player to the comparison and any errors recorded with player comparison add
 */
public class PlayerComparisonAddState {

    private Player lastAddedPlayer;

    private String playerAddError = null;

    /**
     * Constructor for the class
     */
    public PlayerComparisonAddState(){
    }

    /**
     * Sets the last added player to the comparison
     */
    public void setLastAddedPlayer(Player player){
        this.lastAddedPlayer = player;
    }

    /**
     * Sets the error that occurred when attempting to add a player to the comparison
     */
    public void setPlayerAddError(String playerAddError){
        this.playerAddError = playerAddError;
    }

    /**
     * Gets the last added player to the comparison
     * @return the last added player to the comparison
     */
    public Player getLastAddedPlayer(){
        return lastAddedPlayer;
    }

    /**
     * Gets the error that occurred when attempting to add a player to the comparison
     * @return the error that occurred when attempting to add a player to the comparison
     */
    public String getPlayerAddError(){
        return playerAddError;
    }

}
