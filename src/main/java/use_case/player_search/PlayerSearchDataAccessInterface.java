package main.java.use_case.player_search;

import main.java.entity.Player;

import java.io.IOException;

public interface PlayerSearchDataAccessInterface {
    Player search(int player_id) throws IOException;

}
