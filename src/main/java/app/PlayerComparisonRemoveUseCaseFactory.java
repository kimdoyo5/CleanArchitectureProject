package main.java.app;

import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;

public class PlayerComparisonRemoveUseCaseFactory {

    private PlayerComparisonRemoveUseCaseFactory(){}

    public static PlayerComparisonRemoveController createPlayerComparisonRemoveController(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        PlayerComparisonRemoveInteractor removeInteractor = new PlayerComparisonRemoveInteractor(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);
        return new PlayerComparisonRemoveController(removeInteractor);
    }
}
