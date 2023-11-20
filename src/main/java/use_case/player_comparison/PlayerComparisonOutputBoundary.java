package main.java.use_case.player_comparison;

import use_case.player_search.PlayerOutputData;
import use_case.player_search.PlayerComparisonOutputData;

public interface PlayerComparisonOutputBoundary {
    void prepareSuccessView(PlayerComparisonOutputData player);

    void prepareFailView(String error);

}
