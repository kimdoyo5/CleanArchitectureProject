package main.java.use_case.player_comparison;

/**
 * Represents the output data for player comparison.
 * This class encapsulates the data structure needed to represent
 * the comparison of players' statistics in a tabular format.
 */
public class PlayerComparisonOutputData {

    // The array of strings representing player data.
    private final String[][] dataArray;

    /**
     * Constructs a new instance of PlayerComparisonOutputData with the provided data array.
     *
     * @param dataArray The two-dimensional array of strings representing the player comparison data.
     */
    public PlayerComparisonOutputData(String[][] dataArray) {
        this.dataArray = dataArray;
    }

    /**
     * Returns the player comparison data array.
     *
     * @return A two-dimensional array of strings representing the player comparison data.
     */
    public String[][] getDataArray() {
        return dataArray;
    }
}
