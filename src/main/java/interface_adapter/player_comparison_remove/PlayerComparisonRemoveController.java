package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveInputBoundary;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveInputData;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveOutputBoundary;

public class PlayerComparisonRemoveController {

    final PlayerComparisonRemoveInputBoundary playerComparisonRemoveUseCaseInteractor;
    private final PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary;

    public PlayerComparisonRemoveController(
            PlayerComparisonRemoveInputBoundary playerComparisonRemoverUseCaseInteractor,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary
    ) {
        this.playerComparisonRemoveUseCaseInteractor = playerComparisonRemoverUseCaseInteractor;
        this.playerComparisonRemoveOutputBoundary = playerComparisonRemoveOutputBoundary;
    }

    public void execute(int playerId) {
        PlayerComparisonRemoveInputData playerComparisonRemoveInputData = new PlayerComparisonRemoveInputData(playerId);
        playerComparisonRemoveUseCaseInteractor.execute(playerComparisonRemoveInputData);
    }

}
