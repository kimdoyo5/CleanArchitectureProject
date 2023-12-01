package main.java.interface_adapter.player_comparison;

import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;

public class PlayerComparisonController {

    final PlayerComparisonInputBoundary comparisonUseCaseInteractor;

    public PlayerComparisonController(PlayerComparisonInputBoundary comparisonUseCaseInteractor) {
        this.comparisonUseCaseInteractor = comparisonUseCaseInteractor;
    }

    public void execute() {
        // Player comparison interactor
        comparisonUseCaseInteractor.execute();
    }

}