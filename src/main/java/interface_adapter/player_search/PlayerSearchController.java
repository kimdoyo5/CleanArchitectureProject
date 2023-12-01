package main.java.interface_adapter.player_search;

import main.java.use_case.player_search.PlayerSearchInputData;
import main.java.use_case.player_search.PlayerSearchInputDataBoundary;
import main.java.use_case.player_search.PlayerSearchInteractor;

import java.io.IOException;

/**
 * The player search controller that turns the user input to data that the interactor needs and
 * passes it to the interactor to complete the search
 */
public class PlayerSearchController {
    PlayerSearchInputDataBoundary playerSearchInteractor;

    /**
     * Initializes the class
     * @param playerSearchInteractor The interactor of the use case
     */
    public PlayerSearchController(PlayerSearchInputDataBoundary playerSearchInteractor){
        this.playerSearchInteractor = playerSearchInteractor;
    }

    /**
     * Executes the interactor of the use case to begin search
     * @param player_id The input of the user as an integer
     * @throws IOException Errors while searching or invalid input
     */
    public void execute(int player_id) throws IOException {
        PlayerSearchInputData data = new PlayerSearchInputData(player_id);
        playerSearchInteractor.execute(data);
    }

    /**
     * Executes the interactor of the use case to return to main menu
     */
    public void execute(){
        playerSearchInteractor.execute();
    }

}
