package main.java.interface_adapter.player_search;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewState;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.player_search.PlayerOutputData;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;

/**
 * The presenter of the player search use case
 * Takes the results from the interactor and displays it to the user
 */
public class PlayerSearchPresenter implements PlayerSearchOutputBoundary {
    private final PlayerSearchViewModel playerSearchViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlayerDataDisplayViewModel playerDataDisplayViewModel;

    /**
     * Initializes the class
     * @param playerSearchViewModel The view model of player search
     * @param mainMenuViewModel The view model of main menu
     * @param viewManagerModel The view manager
     * @param playerDataDisplayViewModel The view model for player data display
     */
    public PlayerSearchPresenter(PlayerSearchViewModel playerSearchViewModel, MainMenuViewModel mainMenuViewModel, ViewManagerModel viewManagerModel, PlayerDataDisplayViewModel playerDataDisplayViewModel){
        this.playerSearchViewModel = playerSearchViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playerDataDisplayViewModel = playerDataDisplayViewModel;
    }
    @Override
    public void prepareSuccessView(PlayerOutputData player) {
        PlayerSearchState currentState = playerSearchViewModel.getSearchState();
        currentState.setSearch("");
        this.playerSearchViewModel.setSearchState(currentState);
        playerSearchViewModel.firePropertyChanged();
        PlayerDataDisplayViewState state = playerDataDisplayViewModel.getState();
        state.setResult(player.getData());
        state.setLastSearchedPlayer(player.getPlayer());
        this.playerDataDisplayViewModel.setViewState(state);
        playerDataDisplayViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playerDataDisplayViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PlayerSearchState state = playerSearchViewModel.getSearchState();
        state.setSearch_error(error);
        this.playerSearchViewModel.setSearchState(state);
        playerSearchViewModel.firePropertyChanged();
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
