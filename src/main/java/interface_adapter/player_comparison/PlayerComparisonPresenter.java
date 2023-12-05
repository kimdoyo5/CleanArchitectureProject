package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonOutputData;

public class PlayerComparisonPresenter implements PlayerComparisonOutputBoundary {

    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final MainMenuViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlayerComparisonPresenter(PlayerComparisonViewModel playerComparisonViewModel, MainMenuViewModel navigationViewModel, ViewManagerModel viewManagerModel) {
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.navigationViewModel = navigationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PlayerComparisonOutputData playerArray) {
        PlayerComparisonState state = playerComparisonViewModel.getState();
        state.setDataArray(playerArray.getDataArray());
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
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
