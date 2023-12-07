package main.java.app;

import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;

/**
 * Factory for creating the required classes for player comparison remove
 */
public class PlayerComparisonRemoveUseCaseFactory {

    private PlayerComparisonRemoveUseCaseFactory(){}

    /**
     * Creates the controller
     * @param playerComparisonRemoveDataAccessInterface the data access object for player remove
     * @param playerComparisonRemoveOutputBoundary the presenter for player remove
     * @return the controller created
     */
    public static PlayerComparisonRemoveController createPlayerComparisonRemoveController(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        PlayerComparisonRemoveInteractor removeInteractor = new PlayerComparisonRemoveInteractor(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);
        return new PlayerComparisonRemoveController(removeInteractor);
    }
}
