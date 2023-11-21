package main.java.use_case.player_comparison;

import main.java.use_case.player_search.PlayerOutputData;

public interface PlayerComparisonOutputBoundary {
    void prepareSuccessView(PlayerComparisonOutputData player);

    void prepareFailView(String error);

}
