package main.java.use_case.player_comparison_add;

public interface PlayerComparisonAddOutputBoundary {

    void prepareSuccessView(PlayerComparisonAddOutputData user);

    void prepareFailView(String error);

}
