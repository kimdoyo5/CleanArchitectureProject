package main.java.interface_adapter.player_comparison_remove;

import main.java.entity.Player;

public class PlayerComparisonRemoveState {

    private Player lastRemovedPlayer;

    public PlayerComparisonRemoveState(){
    }

    public void setLastRemovedPlayer(Player player){
        this.lastRemovedPlayer = player;
    }

    public Player getLastRemovedPlayer(){
        return lastRemovedPlayer;
    }

}
