package main.java.interface_adapter.player_comparison_add;

import main.java.entity.Player;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputData;

import java.util.ArrayList;

public class PlayerComparisonAddPresenter implements PlayerComparisonAddOutputBoundary {

    /**
     * Presenter for adding players to the comparison use case, takes output from related interactor and prepares the
     * required view
     */
    private final PlayerComparisonAddViewModel playerComparisonAddViewModel;

    /**
     * Constructor for the presenter
     * @param playerComparisonAddViewModel view model for player comparison add use case
     */
    public PlayerComparisonAddPresenter(PlayerComparisonAddViewModel playerComparisonAddViewModel){
        this.playerComparisonAddViewModel = playerComparisonAddViewModel;
    }

    /**
     * Prepares the related view in the case the player was successfully added to the comparison
     * @param response output data from the interactor with the player added to the comparison
     */
    public void prepareSuccessView(PlayerComparisonAddOutputData response) {
        PlayerComparisonAddState playerComparisonAddState = playerComparisonAddViewModel.getState();
        Player addedPlayer = response.getPlayer();
        playerComparisonAddState.setLastAddedPlayer(addedPlayer);
        playerComparisonAddState.setPlayerAddError(null);
        this.playerComparisonAddViewModel.setState(playerComparisonAddState);
        playerComparisonAddViewModel.firePropertyChanged();
    }

    /**
     * Prepares the related view in the case the player was not successfully added to the comparison
     * @param error string described the cause of why the player was not added to the comparison
     */
    public void prepareFailView(String error) {
        PlayerComparisonAddState playerComparisonAddState = playerComparisonAddViewModel.getState();
        playerComparisonAddState.setPlayerAddError(error);
        playerComparisonAddViewModel.firePropertyChanged();
    }


}
