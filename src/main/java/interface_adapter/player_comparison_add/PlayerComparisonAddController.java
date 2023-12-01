package main.java.interface_adapter.player_comparison_add;

import main.java.use_case.player_comparison_add.PlayerComparisonAddInputBoundary;
import main.java.entity.Player;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInputData;

public class PlayerComparisonAddController {

    final PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor;
    public PlayerComparisonAddController(PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor) {
        this.playerComparisonAddUseCaseInteractor = playerComparisonAddUseCaseInteractor;
    }

    public void execute(Player player) {
        PlayerComparisonAddInputData playerComparisonAddInputData = new PlayerComparisonAddInputData(player);
        playerComparisonAddUseCaseInteractor.execute(playerComparisonAddInputData);
    }

}
