package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveInputBoundary;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveInputData;

public class PlayerComparisonRemoveController {

    final PlayerComparisonRemoveInputBoundary playerComparisonRemoveUseCaseInteractor;
    public PlayerComparisonRemoveController(
            PlayerComparisonRemoveInputBoundary playerComparisonRemoverUseCaseInteractor) {
        this.playerComparisonRemoveUseCaseInteractor = playerComparisonRemoverUseCaseInteractor;
    }

    public void execute(int playerId) {
        PlayerComparisonRemoveInputData playerComparisonRemoveInputData = new PlayerComparisonRemoveInputData(playerId);
        playerComparisonRemoveUseCaseInteractor.execute(playerComparisonRemoveInputData);
    }

}
