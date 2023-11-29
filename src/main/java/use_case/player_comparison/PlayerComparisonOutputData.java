package main.java.use_case.player_comparison;

import main.java.entity.Player;

public class PlayerComparisonOutputData {

    private final String[][] dataArray;

    public PlayerComparisonOutputData(String[][] dataArray) {
        this.dataArray = dataArray;
        
    }

    public String[][] getDataArray() {
        return dataArray;
    }
}