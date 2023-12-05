package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInputBoundary;

public class PlayerComparisonRemoveController {

    final PlayerComparisonRemoveInputBoundary playerComparisonRemoveUseCaseInteractor;
    public PlayerComparisonRemoveController(
            PlayerComparisonRemoveInputBoundary playerComparisonRemoverUseCaseInteractor) {
        this.playerComparisonRemoveUseCaseInteractor = playerComparisonRemoverUseCaseInteractor;
    }

    public void execute() {
        playerComparisonRemoveUseCaseInteractor.execute();
    }

}
