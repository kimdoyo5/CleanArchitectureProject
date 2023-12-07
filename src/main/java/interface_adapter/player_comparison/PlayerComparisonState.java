package main.java.interface_adapter.player_comparison;

import main.java.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state for the player comparison feature.
 * This class holds the data and state required for player comparison,
 * including player data, comparison results, and any error messages.
 */
public class PlayerComparisonState {

    private List<Player> players = new ArrayList<>();
    private String[][] dataArray;
    private String playerComparisonError = null;

    public PlayerComparisonState(){}

    public PlayerComparisonState(PlayerComparisonState copy){
        players = copy.getPlayers();
        dataArray = copy.getDataArray();
        playerComparisonError = copy.getPlayerComparisonError();
    }

    /**
     * Sets the error message for player comparison.
     *
     * @param error The error message to be displayed.
     */
    public void setPlayerComparisonError(String error) {
        this.playerComparisonError = error;
    }

    /**
     * Retrieves the list of players.
     *
     * @return A list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the list of players.
     *
     * @param players The list of players to be set.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Retrieves the data array representing player comparison results.
     *
     * @return A 2D array of strings containing the comparison data.
     */
    public String[][] getDataArray() {
        return dataArray;
    }

    /**
     * Sets the data array for player comparison results.
     *
     * @param dataArray The 2D array of strings representing the comparison data.
     */
    public void setDataArray(String[][] dataArray) {
        this.dataArray = dataArray;
    }

    /**
     * Retrieves the current error message for player comparison.
     *
     * @return The error message, or null if there is no error.
     */
    public String getPlayerComparisonError() {
        return playerComparisonError;
    }

}
