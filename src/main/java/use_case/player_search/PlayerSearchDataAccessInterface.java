package use_case.player_search;

import entity.Player;

import java.io.IOException;

public interface PlayerSearchDataAccessInterface {
    Player search(int player_id) throws IOException;

}
