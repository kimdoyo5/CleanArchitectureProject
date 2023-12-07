package main.java.app;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchController;
import main.java.interface_adapter.player_search.PlayerSearchPresenter;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchInputDataBoundary;
import main.java.use_case.player_search.PlayerSearchInteractor;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;
import main.java.view.PlayerSearchView;

/**
 * Factory for creating the methods needed for player search use case
 */
public class PlayerSearchUseCaseFactory {
    private PlayerSearchUseCaseFactory(){}


    /**
     * Creates the view for player search
     * @param viewManagerModel The view manager model
     * @param mainMenuViewModel the viewmodel for main menu
     * @param playerSearchViewModel the view model for player search
     * @param playerSearchDataAccessInterface the data access object for player search
     * @param idSearchDataAccessInterface the data access object for id search
     * @param playerDataDisplayViewModel the view model for data display
     * @return the created view
     */
    public static PlayerSearchView create (ViewManagerModel viewManagerModel,
                                           MainMenuViewModel mainMenuViewModel,
                                           PlayerSearchViewModel playerSearchViewModel,
                                           PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                           IDSearchDataAccessInterface idSearchDataAccessInterface,
                                           PlayerDataDisplayViewModel playerDataDisplayViewModel){
        PlayerSearchController playerSearchController = createPlayerSearchController(viewManagerModel, mainMenuViewModel, playerSearchViewModel, playerSearchDataAccessInterface, idSearchDataAccessInterface, playerDataDisplayViewModel);
        return new PlayerSearchView(playerSearchController, playerSearchViewModel);
    }

    private static PlayerSearchController createPlayerSearchController(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, PlayerSearchViewModel playerSearchViewModel, PlayerSearchDataAccessInterface playerSearchDataAccessInterface, IDSearchDataAccessInterface idSearchDataAccessInterface, PlayerDataDisplayViewModel playerDataDisplayViewModel){
        PlayerSearchOutputBoundary playerSearchOutputBoundary = new PlayerSearchPresenter(playerSearchViewModel, mainMenuViewModel, viewManagerModel, playerDataDisplayViewModel);
        PlayerSearchInputDataBoundary playerSearchInputDataBoundary = new PlayerSearchInteractor(playerSearchDataAccessInterface, playerSearchOutputBoundary, idSearchDataAccessInterface);
        return new PlayerSearchController(playerSearchInputDataBoundary);
    }
}
