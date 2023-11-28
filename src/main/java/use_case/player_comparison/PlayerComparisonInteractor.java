package main.java.use_case.player_comparison;

import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonPresenter;

import java.io.IOException;

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

        int playersAdded = playerComparisonDataAccessInterface.playersAdded();

        if (playersAdded == 2) { // Whether there's 2+ players
            // Player 1 statistic
            // Player 2 statistic


        } if (playersAdded == 3) {

        } if (playersAdded == 4) {

        } else {
            comparisonPresenter.prepareFailView("You need to select at least 2 players.");
        }

    }
}
