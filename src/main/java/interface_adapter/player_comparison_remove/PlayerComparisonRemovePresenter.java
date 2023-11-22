package main.java.interface_adapter.player_comparison_remove;

import main.java.entity.Player;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveOutputData;

public class PlayerComparisonRemovePresenter implements PlayerComparisonRemoveOutputBoundary {

    private final PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel;

    public PlayerComparisonRemovePresenter(PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel){
        this.playerComparisonRemoveViewModel = playerComparisonRemoveViewModel;
    }

    public void prepareSuccessView(PlayerComparisonRemoveOutputData response) {
        PlayerComparisonRemoveState playerComparisonRemoveState = playerComparisonRemoveViewModel.getState();
        Player removedPlayer = response.getPlayer();
        playerComparisonRemoveState.setLastRemovedPlayer(removedPlayer);
        this.playerComparisonRemoveViewModel.setState(playerComparisonRemoveState);
        playerComparisonRemoveViewModel.firePropertyChanged();
    }

}
