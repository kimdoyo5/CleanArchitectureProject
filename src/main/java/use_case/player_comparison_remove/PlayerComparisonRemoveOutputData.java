package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

/**
 * Output data for the player comparison remove use case
 * contains the list of names of the players that have been removed from the comparison
 */
public class PlayerComparisonRemoveOutputData {

    final private List<String> removedPlayers;

    /**
     * Constructor for the class
     * @param removedPlayers the list of names of the players that have been removed from the comparison
     */
    public PlayerComparisonRemoveOutputData(List<String> removedPlayers){
        this.removedPlayers = removedPlayers;
    }

    /**
     * gets the list of names of the players that have been removed from the comparison
     * @return the list of names of the players that have been removed from the comparison
     */
    public List<String> getRemovedPlayers(){
        return this.removedPlayers;
    }

}
