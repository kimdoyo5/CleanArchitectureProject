package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputData;

import java.util.List;


/**
 * Presenter for removing all players from the comparison use case, takes output from related interactor and prepares
 * the required view
 */
public class PlayerComparisonRemovePresenter implements PlayerComparisonRemoveOutputBoundary {

    private final PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel;

    /**
     * Constructor for the presenter
     * @param playerComparisonRemoveViewModel view model for player comparison remove use case
     */
    public PlayerComparisonRemovePresenter(PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel){
        this.playerComparisonRemoveViewModel = playerComparisonRemoveViewModel;
    }

    /**
     * Prepares the related view in the case the players were successfully removed from the comparison
     * @param response output data from the interactor with the players removed from the comparison
     */
    public void prepareSuccessView(PlayerComparisonRemoveOutputData response) {
        PlayerComparisonRemoveState playerComparisonRemoveState = playerComparisonRemoveViewModel.getState();
        List<String> playerNames = response.getRemovedPlayers();
        playerComparisonRemoveState.setLastRemovedPlayer(playerNames);
        playerComparisonRemoveState.setPlayerRemoveError(null);
        this.playerComparisonRemoveViewModel.setState(playerComparisonRemoveState);
        playerComparisonRemoveViewModel.firePropertyChanged();
    }

    /**
     * Prepares the related view in the case there were originally no players in the comparison
     * @param error string described how there were originally no players in the comparison
     */
    public void prepareFailView(String error) {
        PlayerComparisonRemoveState playerComparisonRemoveState = playerComparisonRemoveViewModel.getState();
        playerComparisonRemoveState.setPlayerRemoveError(error);
        playerComparisonRemoveViewModel.firePropertyChanged();
    }

}
