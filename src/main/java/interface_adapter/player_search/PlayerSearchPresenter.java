package main.java.interface_adapter.player_search;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewState;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.use_case.player_search.PlayerOutputData;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;

import java.util.HashMap;
import java.util.Map;

public class PlayerSearchPresenter implements PlayerSearchOutputBoundary {
    private final PlayerSearchViewModel playerSearchViewModel;
    private final NavigationViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlayerDataDisplayViewModel playerDataDisplayViewModel;

    public PlayerSearchPresenter(PlayerSearchViewModel playerSearchViewModel, NavigationViewModel navigationViewModel, ViewManagerModel viewManagerModel, PlayerDataDisplayViewModel playerDataDisplayViewModel){
        this.playerSearchViewModel = playerSearchViewModel;
        this.navigationViewModel = navigationViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playerDataDisplayViewModel = playerDataDisplayViewModel;
    }
    @Override
    public void prepareSuccessView(PlayerOutputData player) {
        PlayerDataDisplayViewState state = playerDataDisplayViewModel.getState();
        state.setResult(player.getData());
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
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
