package main.java.use_case.player_comparison_remove;

public interface PlayerComparisonRemoveOutputBoundary {

    void prepareSuccessView(PlayerComparisonRemoveOutputData players);

    void prepareFailView(String error);

}
