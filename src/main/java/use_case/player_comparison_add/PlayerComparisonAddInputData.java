package main.java.use_case.player_comparison_add;

public class PlayerComparisonAddInputData {

    final private Player player;                      //Player attribute is wip: specific player attributes subject to change

    public PlayerComparisonAddInputData(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }
}
