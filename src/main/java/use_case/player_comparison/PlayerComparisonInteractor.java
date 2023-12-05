package main.java.use_case.player_comparison;

import main.java.entity.PlayerFactory;

import java.util.List;
import java.util.Map;

public class PlayerComparisonInteractor implements PlayerComparisonInputBoundary {
    final PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface;
    final PlayerComparisonOutputBoundary playerComparisonOutputBoundary;
    final PlayerFactory playerDataFactory;

    PlayerComparisonOutputBoundary playerComparisonPresenter;

    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary playerComparisonOutputBoundary,
                                      PlayerFactory playerFactory) {
        this.playerComparisonDataAccessInterface = playerComparisonDataAccessInterface;
        this.playerComparisonOutputBoundary = playerComparisonOutputBoundary;
        this.playerDataFactory = playerFactory;
    }


    @Override
    public void back() {
        playerComparisonOutputBoundary.back();
    }

    @Override
    public void execute() {
        int playersAdded = playerComparisonDataAccessInterface.getSize();
        List<String> playerList = playerComparisonDataAccessInterface.getAllPlayerNames();
        Map<String,String> leaders = playerComparisonDataAccessInterface.getLeaders();

        if (playersAdded < 2) {
            playerComparisonPresenter.prepareFailView("You need to select at least 2 players.");
            return;
        }

        String[][] dataArray = getArray(playersAdded, playerList, leaders);

        PlayerComparisonOutputData playerComparisonOutputData = new PlayerComparisonOutputData(dataArray);
        playerComparisonPresenter.prepareSuccessView(playerComparisonOutputData);
    }

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

