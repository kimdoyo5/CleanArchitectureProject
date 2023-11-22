package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

public class PlayerComparisonAddOutputData {

    final private Player player;

    public PlayerComparisonAddOutputData(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

}
