package main.java.use_case.player_comparison;

import entity.Player;
import entity.PlayerFactory;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;
import java.io.IOException;

public class PlayerComparisonInteractor extends PlayerComparisonInputBoundary {
    PlayerComparisonDataAccessInterface comparisonDataAccessObject;
    PlayerComparisonOutputBoundary playerComparisonOutputBoundary;
    PlayerFactory playerDataFactory;

    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface comparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary comparisonOutputBoundary,
                                      PlayerFactory playerFactory) {
        this.comparisonDataAccessObject = comparisonDataAccessInterface;
        this.playerComparisonOutputBoundary = comparisonOutputBoundary;
        this.playerDataFactory = playerFactory;
    }

    @Override
    public void execute(PlayerComparisonInputData playerComparisonInputData) throws IOException {

        if (playerComparisonInputData.inputsin) {
            // Player 1 statistic
            // Player 2 statistic

        } else {
            playerComparisonOutputBoundary.prepareFailView("Did not select 2 players.");
        }



    }
}
