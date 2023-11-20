package main.java.interface_adapter.player_comparison_add;

import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputData;

import java.util.ArrayList;

public class PlayerComparisonAddPresenter implements PlayerComparisonAddOutputBoundary {

    private final PlayerComparisonAddViewModel playerComparisonAddViewModel;

    public PlayerComparisonAddPresenter(PlayerComparisonAddViewModel playerComparisonAddViewModel){
        this.playerComparisonAddViewModel = playerComparisonAddViewModel;
    }

    public void prepareSuccessView(PlayerComparisonAddOutputData response) {
        PlayerComparisonAddState playerComparisonAddState = playerComparisonAddViewModel.getState();
        Player addedPlayer = response.getPlayer();
        ArrayList<Player> players = playerComparisonAddState.getPlayers();
        players.add(addedPlayer);
        playerComparisonAddState.setPlayers(players);
        this.playerComparisonAddViewModel.setState(playerComparisonAddState);
        playerComparisonAddViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        PlayerComparisonAddState playerComparisonAddState = playerComparisonAddViewModel.getState();
        playerComparisonAddState.setPlayerAddError(error);
        playerComparisonAddViewModel.firePropertyChanged();
    }


}
