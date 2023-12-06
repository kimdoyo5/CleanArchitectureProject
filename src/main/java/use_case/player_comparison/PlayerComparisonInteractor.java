package main.java.use_case.player_comparison;

import main.java.entity.PlayerFactory;

import java.util.List;
import java.util.Map;

/**
 * Interactor class for player comparison.
 * This class acts as an interactor in the clean architecture, handling the business logic
 * for comparing players. It communicates with data access interfaces and prepares
 * data for presentation.
 */
public class PlayerComparisonInteractor implements PlayerComparisonInputBoundary {
    final PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface;
    final PlayerComparisonOutputBoundary playerComparisonOutputBoundary;


    /**
     * Constructs a PlayerComparisonInteractor with specified data access, output boundary, and player factory.
     *
     * @param playerComparisonDataAccessInterface Data access interface for player comparison data.
     * @param playerComparisonOutputBoundary      Output boundary interface for sending results to the presenter.
     */
    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary playerComparisonOutputBoundary) {
        this.playerComparisonDataAccessInterface = playerComparisonDataAccessInterface;
        this.playerComparisonOutputBoundary = playerComparisonOutputBoundary;
    }

    /**
     * Handles the request to go back from the player comparison view.
     * This method triggers the process to return to the main menu or previous view.
     */
    @Override
    public void back() {
        playerComparisonOutputBoundary.back();
    }

    /**
     * Executes the process of comparing players.
     * This method contains the logic to fetch and compare player data, and prepares
     * the output for presentation.
     */
    @Override
    public void execute() {
        int playersAdded = playerComparisonDataAccessInterface.getSize();
        List<String> playerList = playerComparisonDataAccessInterface.getAllPlayerNames();
        Map<String,String> leaders = playerComparisonDataAccessInterface.getLeaders();

        if (playersAdded < 2) {
            playerComparisonOutputBoundary.prepareFailView("You need to select at least 2 players.");
        }

        String[][] dataArray = getArray(playersAdded, playerList, leaders);

        PlayerComparisonOutputData playerComparisonOutputData = new PlayerComparisonOutputData(dataArray);
        playerComparisonOutputBoundary.prepareSuccessView(playerComparisonOutputData);
    }

    /**
     * Constructs a 2D array of player statistics for comparison.
     *
     * @param playersAdded Number of players added for comparison.
     * @param playerList   List of player names for comparison.
     * @param leaders      Map of statistical leaders in different categories.
     * @return A 2D array containing player statistics.
     */
    private String[][] getArray(int playersAdded, List<String> playerList, Map<String,String> leaders) {
        String[] statKeys = {
                "hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg",
                "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS" };
        int numRows = statKeys.length + 1; // Extra row for header
        int numColumns = Math.min(playersAdded, 4) + 2; // Players + header and leaders
        String[][] dataArray = new String[numRows][numColumns];

        // Set header row
        dataArray[0][0] = "Stat/Player";
        for (int i = 0; i < playersAdded && i < 4; i++) {
            dataArray[0][i + 1] = "Player " + (i + 1);
        }
        dataArray[0][numColumns - 1] = "Leaders";

        // Set data rows
        for (int i = 0; i < statKeys.length; i++) {
            String key = statKeys[i];
            dataArray[i + 1][0] = key;
            for (int j = 1; j <= playersAdded && j <= 4; j++) {
                Map<String, Double> playerStats = playerComparisonDataAccessInterface.getSimplified(playerList.get(j - 1));
                dataArray[i + 1][j] = playerStats.containsKey(key) ? playerStats.get(key).toString() : "N/A";
            }
            dataArray[i + 1][numColumns - 1] = leaders.getOrDefault(key, "N/A");
        }

        return dataArray;
    }
}
