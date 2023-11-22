package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

public class PlayerComparisonAddOutputData {

    final private Player player;                      //Player attribute is wip: specific player attributes subject to change

    public PlayerComparisonAddOutputData(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

}
