package main.java.use_case.player_comparison_add;

public interface PlayerComparisonAddOutputBoundary {

    void prepareSuccessView(PlayerComparisonAddOutputData playerComparisonAddOutputData);

    void prepareFailView(String error);

}
