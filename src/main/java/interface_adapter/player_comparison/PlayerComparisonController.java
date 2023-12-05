package main.java.interface_adapter.player_comparison;

import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;

public class PlayerComparisonController {

    final PlayerComparisonInputBoundary playerComparisonUseCaseInteractor;

    public PlayerComparisonController(PlayerComparisonInputBoundary comparisonUseCaseInteractor) {
        this.playerComparisonUseCaseInteractor = comparisonUseCaseInteractor;
    }

    public void execute() {
        // Player comparison interactor
        playerComparisonUseCaseInteractor.execute();
    }

    public void back() {
        // Back to main menu
        playerComparisonUseCaseInteractor.back();
    }

}