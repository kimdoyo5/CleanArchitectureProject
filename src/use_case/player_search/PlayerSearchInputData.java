package use_case.player_search;

public class PlayerSearchInputData {
    private String name = "";

    private int player_id = 0;

    public PlayerSearchInputData(String name){
        this.name = name.toLowerCase();
    }

    public PlayerSearchInputData(int player_id){
        this.player_id = player_id;
    }

    public int getPlayer_id(){
        return player_id;
    }

    public String getName(){
        return name;
    }
}
