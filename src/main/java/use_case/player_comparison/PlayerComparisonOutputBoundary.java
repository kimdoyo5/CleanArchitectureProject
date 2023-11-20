package main.java.use_case.player_comparison;

import main.java.use_case.player_comparison.PlayerComparisonOutputData;

public interface PlayerComparisonOutputBoundary {
    void prepareSuccessView(PlayerComparisonOutputData player);

    void prepareFailView(String error);

}
