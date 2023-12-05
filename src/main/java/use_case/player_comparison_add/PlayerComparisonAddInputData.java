package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

/**
 * Input data for the player comparison add use case
 * contains a player object to be added to the comparison
 */
public class PlayerComparisonAddInputData {

    final private Player player;

    /**
     * Constructor for the class
     * @param player a player object to be added to the comparison
     */
    public PlayerComparisonAddInputData(Player player){
        this.player = player;
    }

    /**
     * gets the player to be added to the comparison
     * @return the player to be added to the comparison
     */
    public Player getPlayer(){
        return this.player;
    }
}
