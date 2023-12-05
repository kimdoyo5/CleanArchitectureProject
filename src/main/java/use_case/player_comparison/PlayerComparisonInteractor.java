package main.java.use_case.player_comparison;

import main.java.entity.PlayerFactory;

import java.util.List;
import java.util.Map;

public class PlayerComparisonInteractor implements PlayerComparisonInputBoundary {
    final PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface;
    final PlayerComparisonOutputBoundary playerComparisonPresenter;
    final PlayerFactory playerDataFactory;

    PlayerComparisonOutputBoundary comparisonPresenter;

    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary playerComparisonOutputBoundary,
                                      PlayerFactory playerFactory) {
        this.playerComparisonDataAccessInterface = playerComparisonDataAccessInterface;
        this.playerComparisonPresenter = playerComparisonOutputBoundary;
        this.playerDataFactory = playerFactory;
    }

    @Override
    public void execute() {
        int playersAdded = playerComparisonDataAccessInterface.getSize();
        List<String> playerList = playerComparisonDataAccessInterface.getAllPlayerNames();
        Map<String,String> leaders = playerComparisonDataAccessInterface.getLeaders();

        if (playersAdded >= 2) { // Whether there are 2+ players
            String player1name = playerList.get(0);
            Map<String, Double> player1 = playerComparisonDataAccessInterface.getSimplified(player1name);
            String player2name = playerList.get(1);
            Map<String, Double> player2 = playerComparisonDataAccessInterface.getSimplified(player2name);

        } if (playersAdded >= 3) {
            String player3name = playerList.get(2);
            Map<String, Double> player3 = playerComparisonDataAccessInterface.getSimplified(player3name);

        } if (playersAdded >= 4) {
            String player4name = playerList.get(3);
            Map<String, Double> player4 = playerComparisonDataAccessInterface.getSimplified(player4name);

        } else {
            comparisonPresenter.prepareFailView("You need to select at least 2 players.");
        }

        String[][] dataArray = getArray(playersAdded, playerList, leaders);

        PlayerComparisonOutputData playerComparisonOutputData = new PlayerComparisonOutputData(dataArray);
        playerComparisonPresenter.prepareSuccessView(playerComparisonOutputData);

    }

    private String[][] getArray(int playersAdded, List<String>playerList, Map<String,String>leaders) {
        String[] statKeys = {
                "hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg",
                "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS" };
        int numRows = statKeys.length + 1; // Extra row for header
        int numColumns = Math.min(playersAdded, 4) + 2; // Players + header and leaders
        String[][] dataArray = new String[numRows][numColumns];

        // Set header row
        dataArray[0][0] = "Stat/Player";
        for (int i = 0; i < playersAdded && i < 4; i++) {
            dataArray[0][i + 1] = playerList.get(i);
        }
        dataArray[0][numColumns - 1] = "Leaders";

        // Set key rows
        for (int i = 0; i < statKeys.length; i++) {
            dataArray[i + 1][0] = statKeys[i];
        }

        for (int i = 1; i < numRows; i++) {
            String key = dataArray[i][0];
            for (int j = 1; j <= playersAdded && j <= 4; j++) {
                Map<String, Double> playerStats = playerComparisonDataAccessInterface.getSimplified(playerList.get(j - 1));
                dataArray[i][j] = playerStats.containsKey(key) ? playerStats.get(key).toString() : "N/A";
            }
            dataArray[i][numColumns - 1] = leaders.getOrDefault(key, "N/A");
        }
        return dataArray;
    }
}

