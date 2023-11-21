package main.java.interface_adapter.player_comparison;

import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonOutputData;

public class PlayerComparisonPresenter implements PlayerComparisonOutputBoundary {


    @Override
    public void prepareSuccessView(PlayerComparisonOutputData player) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
