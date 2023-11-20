package main.java.interface_adapter.player_comparison_add;

import main.java.use_case.player_comparison_add.PlayerComparisonAddInputBoundary;

public class PlayerComparisonAddController {

    final PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor;
    public PlayerComparisonAddController(PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor) {
        this.playerComparisonAddUseCaseInteractor = playerComparisonAddUseCaseInteractor;
    }

    public void execute() {
        playerComparisonAddUseCaseInteractor.execute();
    }

}
