package main.java.app;

import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.entity.PlayerFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonPresenter;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveViewModel;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInputBoundary;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonDataAccessInterface;
import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonInteractor;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInteractor;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;
import main.java.view.PlayerComparisonView;

/**
 * Factory for player comparison use case
 */
public class PlayerComparisonUseCaseFactory {

    private PlayerComparisonUseCaseFactory(){}

    /**
     * Creates the view
     * @param playerComparisonViewModel view model for player comparison
     * @param mainMenuViewModel view model for main menu
     * @param viewManagerModel view manager
     * @param playerComparisonDataAccessInterface data access object for player comparison
     * @param playerComparisonRemoveOutputBoundary presenter for player comparison
     * @param playerComparisonRemoveDataAccessInterface data access object for player comparison
     * @param playerComparisonRemoveViewModel view model for player remove
     * @return The created view
     */
    public static PlayerComparisonView create (
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel mainMenuViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary,
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel
    ) {
        PlayerComparisonRemoveController playerComparisonRemoveController = PlayerComparisonRemoveUseCaseFactory.createPlayerComparisonRemoveController(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);
        PlayerComparisonController playerComparisonController = createPlayerComparisonController(
                playerComparisonViewModel, mainMenuViewModel, viewManagerModel, playerComparisonDataAccessInterface);


        return new PlayerComparisonView(playerComparisonController, playerComparisonRemoveController, playerComparisonViewModel, playerComparisonRemoveViewModel);
    }

    private static PlayerComparisonController createPlayerComparisonController(
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel navigationViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface
    ) {
        PlayerComparisonOutputBoundary playerComparisonOutputBoundary = new PlayerComparisonPresenter(
                playerComparisonViewModel, navigationViewModel, viewManagerModel);

        PlayerComparisonInputBoundary playerComparisonInputBoundary = new PlayerComparisonInteractor(
                playerComparisonDataAccessInterface, playerComparisonOutputBoundary);

        return new PlayerComparisonController(playerComparisonInputBoundary);
    }



}

