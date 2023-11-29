package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

public class PlayerComparisonRemoveOutputData {

    final private Player player;

    public PlayerComparisonRemoveOutputData(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

}
