package interface_adapter.player_comparison;

import use_case.player_comparison.PlayerComparisonInputBoundary;
import use_case.player_comparison.PlayerComparisonInputData;

public class PlayerComparisonController {

    final PlayerComparisonInputBoundary comparisonUseCaseInteractor;
    public PlayerComparisonController(PlayerComparisonInputBoundary comparisonUseCaseInteractor) {
        this.comparisonUseCaseInteractor = comparisonUseCaseInteractor;
    }

    public void execute(String player1, String player2) {
        PlayerComparisonInputData playerComparisonInputData = new PlayerComparisonInputData(player1, player2);

        comparisonUseCaseInteractor.execute(playerComparisonInputData);
    }

}