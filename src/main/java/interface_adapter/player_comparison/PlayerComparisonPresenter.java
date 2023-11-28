package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddPresenter;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonOutputData;
import main.java.view.PlayerComparisonView;

public class PlayerComparisonPresenter implements PlayerComparisonOutputBoundary {

    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final NavigationViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlayerComparisonPresenter(PlayerComparisonViewModel playerComparisonViewModel, NavigationViewModel navigationViewModel, ViewManagerModel viewManagerModel) {
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.navigationViewModel = navigationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PlayerComparisonOutputData player) {


        this.playerSearchViewModel.setSearchState(state);
    }

    @Override
    public void prepareFailView(String error) {
        PlayerComparisonState playerComparisonState = playerComparisonViewModel.getState();
        playerComparisonState.setPlayerComparisonError(error);
        this.playerComparisonViewModel.setComparisonState(state);
        playerComparisonViewModel.firePropertyChanged();
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
