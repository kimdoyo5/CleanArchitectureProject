package use_case.PlayerSearch;

import entity.PlayerData;

public interface PlayerSearchDataAccessInterface {
    public PlayerData search(int player_id);
}
