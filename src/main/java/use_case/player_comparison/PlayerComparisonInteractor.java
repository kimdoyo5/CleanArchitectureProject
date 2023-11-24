package main.java.use_case.player_comparison;

import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonPresenter;

import java.io.IOException;

public class PlayerComparisonInteractor implements PlayerComparisonInputBoundary {
    final PlayerComparisonDataAccessInterface comparisonDataAccessObject;
    final PlayerFactory playerDataFactory;

    PlayerComparisonOutputBoundary comparisonPresenter;

    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface comparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary comparisonOutputBoundary,
                                      PlayerFactory playerFactory) {
        this.comparisonDataAccessObject = comparisonDataAccessInterface;
        this.comparisonPresenter = comparisonOutputBoundary;
        this.playerDataFactory = playerFactory;
    }

    @Override
    public void execute(PlayerComparisonInputData playerComparisonInputData) {

        if (playerComparisonInputData.inputsin) {
            // Player 1 statistic
            // Player 2 statistic

        } else {
            comparisonPresenter.prepareFailView("Did not select 2 players.");
        }



    }
}
