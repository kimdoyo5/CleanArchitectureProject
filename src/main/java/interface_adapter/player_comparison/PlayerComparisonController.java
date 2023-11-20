package interface_adapter.player_comparison;

import use_case.player_comparison.PlayerComparisonInputBoundary;
import use_case.player_comparison.PlayerComparisonInputData;

import java.io.IOException;

public class PlayerComparisonController {

    final PlayerComparisonInputBoundary comparisonUseCaseInteractor;
    public PlayerComparisonController(PlayerComparisonInputBoundary comparisonUseCaseInteractor) {
        this.comparisonUseCaseInteractor = comparisonUseCaseInteractor;
    }

    public void execute(int player_id1, int player_id2) throws IOException {
        PlayerComparisonInputData playerComparisonInputData = new PlayerComparisonInputData(player_id1, player_id2);

        comparisonUseCaseInteractor.execute(playerComparisonInputData);
    }

}