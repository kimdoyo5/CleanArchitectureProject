package main.java.use_case.player_search;

public class PlayerSearchInputData {

    private final int player_id;

    public PlayerSearchInputData(int player_id){
        this.player_id = player_id;
    }

    public int getPlayer_id(){
        return player_id;
    }

}
