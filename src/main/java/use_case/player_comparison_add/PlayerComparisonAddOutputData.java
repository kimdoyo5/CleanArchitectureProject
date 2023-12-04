package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

/**
 * Output data for the player comparison add use case
 * contains a player object to has been added to the comparison
 */
public class PlayerComparisonAddOutputData {

    final private Player player;

    /**
     * Constructor for the class
     * @param player the player object that has been added to the comparison
     */
    public PlayerComparisonAddOutputData(Player player){
        this.player = player;
    }

    /**
     * gets the player that has been added to the comparison
     * @return the player that has been added to the comparison
     */
    public Player getPlayer(){
        return this.player;
    }

}
