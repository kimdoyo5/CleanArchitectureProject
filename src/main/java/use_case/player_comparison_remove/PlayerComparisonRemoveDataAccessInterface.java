package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

public interface PlayerComparisonRemoveDataAccessInterface {

    List<String> removedPlayers();

    /**
     * Gets the amount of players in the comparison
     * @return an int describing the amount of players in the comparison
     */
    int getSize();

    boolean add(Player player);

}
