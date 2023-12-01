package main.java.use_case.player_search;

/**
 * The input data of the player search use case
 */
public class PlayerSearchInputData {

    private final int player_id;

    /**
     * initializes the class
     * @param player_id the id of the player the user wants to search for
     */
    public PlayerSearchInputData(int player_id){
        this.player_id = player_id;
    }

    /**
     * Retrieve the player id saved
     * @return the id of the player the user is searching for
     */
    public int getPlayer_id(){
        return player_id;
    }

}
