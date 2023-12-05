package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputData;

import java.util.List;

public class PlayerComparisonRemovePresenter implements PlayerComparisonRemoveOutputBoundary {

    private final PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel;

    public PlayerComparisonRemovePresenter(PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel){
        this.playerComparisonRemoveViewModel = playerComparisonRemoveViewModel;
    }

    public void prepareSuccessView(PlayerComparisonRemoveOutputData response) {
        PlayerComparisonRemoveState playerComparisonRemoveState = playerComparisonRemoveViewModel.getState();
        List<String> playerNames = response.getRemovedPlayers();
        playerComparisonRemoveState.setLastRemovedPlayer(playerNames);
        playerComparisonRemoveState.setPlayerRemoveError(null);
        this.playerComparisonRemoveViewModel.setState(playerComparisonRemoveState);
        playerComparisonRemoveViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        PlayerComparisonRemoveState playerComparisonRemoveState = playerComparisonRemoveViewModel.getState();
        playerComparisonRemoveState.setPlayerRemoveError(error);
        playerComparisonRemoveViewModel.firePropertyChanged();
    }

}
