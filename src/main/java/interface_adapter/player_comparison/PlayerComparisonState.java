package main.java.interface_adapter.player_comparison;

import main.java.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerComparisonState {

    // These variables need to be declared in the class.
    private List<Player> players = new ArrayList<>();

    private String[][] dataArray;

    private String playerComparisonError = null;

    public void setPlayerComparisonError(String error) {
        this.playerComparisonError = error;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String[][] getDataArray() {
        return dataArray;
    }

    public void setDataArray(String[][] dataArray) {
        this.dataArray = dataArray;
    }

    public String getPlayerComparisonError() {
        return playerComparisonError;
    }

}
