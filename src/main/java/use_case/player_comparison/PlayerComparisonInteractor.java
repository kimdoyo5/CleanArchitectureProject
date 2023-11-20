package use_case.player_comparison;

import entity.Player;
import entity.PlayerFactory;
import interface_adapter.player_comparison.PlayerComparisonController;
import java.io.IOException;

public class PlayerComparisonInteractor implements PlayerComparisonInputBoundary {
    final PlayerComparisonDataAccessInterface comparisonDataAccessObject;
    final PlayerComparisonOutputBoundary playerComparisonOutputBoundary;
    final PlayerFactory playerDataFactory;

    public PlayerComparisonInteractor(PlayerComparisonDataAccessInterface comparisonDataAccessInterface,
                                      PlayerComparisonOutputBoundary comparisonOutputBoundary,
                                      PlayerFactory playerFactory) {
        this.comparisonDataAccessObject = comparisonDataAccessInterface;
        this.comparisonPresenter = comparisonOutputBoundary;
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
