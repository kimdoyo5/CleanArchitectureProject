package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonOutputData;

/**
 * Presenter class for player comparison.
 * This class acts as a presenter in the MVC pattern, responsible for handling
 * the presentation logic for the player comparison feature. It communicates
 * with the output boundary of the player comparison use case.
 */
public class PlayerComparisonPresenter implements PlayerComparisonOutputBoundary {

    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final MainMenuViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a PlayerComparisonPresenter with the specified view models and view manager.
     *
     * @param playerComparisonViewModel The view model for player comparison.
     * @param navigationViewModel       The view model for main menu navigation.
     * @param viewManagerModel          The model for managing different views.
     */
    public PlayerComparisonPresenter(PlayerComparisonViewModel playerComparisonViewModel, MainMenuViewModel navigationViewModel, ViewManagerModel viewManagerModel) {
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.navigationViewModel = navigationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for a successful player comparison.
     * Updates the player comparison state with the comparison results and notifies observers.
     *
     * @param playerArray The data representing the comparison results.
     */
    @Override
    public void prepareSuccessView(PlayerComparisonOutputData playerArray) {
        PlayerComparisonState state = playerComparisonViewModel.getState();
        state.setDataArray(playerArray.getDataArray());
        this.playerComparisonViewModel.setState(state);
        playerComparisonViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed player comparison.
     * Updates the player comparison state with an error message and notifies observers.
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareFailView(String error) {
        PlayerComparisonState state = playerComparisonViewModel.getState();
        state.setPlayerComparisonError(error);
        this.playerComparisonViewModel.setState(state);
        playerComparisonViewModel.firePropertyChanged();
    }

    /**
     * Handles the request to go back from the player comparison view.
     * Changes the active view to the main menu or previous view and notifies observers.
     */
    @Override
    public void back() {
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

