package use_case.player_search;

import entity.Player;

public interface PlayerSearchDataAccessInterface {
    Player search(int player_id);

    Player search(String player_name);
}
