package main.java.use_case.player_comparison;

public interface PlayerComparisonOutputBoundary {
    void prepareSuccessView(PlayerComparisonOutputData dataArray);

    void prepareFailView(String error);

    void back();
}
