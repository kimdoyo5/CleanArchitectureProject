package data_access;

import entity.Player;
import use_case.player_search.PlayerSearchDataAccessInterface;

public class APIDataAccessObject implements PlayerSearchDataAccessInterface {
    @Override
    public Player search(int player_id) {
        return null;
    }
}
