package interface_adapter.player_search;

import use_case.player_search.PlayerSearchInputData;
import use_case.player_search.PlayerSearchInputDataBoundary;
import use_case.player_search.PlayerSearchInteractor;

import java.io.IOException;

public class PlayerSearchController {
    PlayerSearchInputDataBoundary playerSearchInteractor;
    public PlayerSearchController(PlayerSearchInputDataBoundary playerSearchInteractor){
        this.playerSearchInteractor = playerSearchInteractor;
    }

    public void execute(int player_id) throws IOException {
        PlayerSearchInputData data = new PlayerSearchInputData(player_id);
        playerSearchInteractor.execute(data);
    }

}
