package main.java.use_case.player_comparison;

public class PlayerComparisonOutputData {

    private final String[][] dataArray;

    public PlayerComparisonOutputData(String[][] dataArray) {
        this.dataArray = dataArray;
        
    }

    public String[][] getDataArray() {
        return dataArray;
    }
}