package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonOutputData;

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
        PlayerComparisonState state = playerComparisonViewModel.getState();
        state.setDataArray(player.getDataArray());
        this.playerComparisonViewModel.setState(state);
        playerComparisonViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PlayerComparisonState state = playerComparisonViewModel.getState();
        state.setPlayerComparisonError(error);
        this.playerComparisonViewModel.setState(state);
        playerComparisonViewModel.firePropertyChanged();
    }

    @Override
    public void back() {
        // Navigation logic, as currently implemented
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
