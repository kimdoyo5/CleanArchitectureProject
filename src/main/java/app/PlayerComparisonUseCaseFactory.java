package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.id_search.IDSearchController;
import main.java.interface_adapter.id_search.IDSearchPresenter;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import
import main.java.interface_adapter.player_search.PlayerSearchController;
import main.java.interface_adapter.player_search.PlayerSearchPresenter;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.id_search.IDSearchInputBoundary;
import main.java.use_case.id_search.IDSearchInteractor;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchInputDataBoundary;
import main.java.use_case.player_search.PlayerSearchInteractor;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;
import main.java.view.IDSearchView;
import main.java.view.PlayerSearchView;

public class PlayerComparisonUseCaseFactory {

    private PlayerComparisonUseCaseFactory(){}

    public static PlayerComparisonView create (PlayerComparisonViewModel playerComparisonViewModel,
                                               NavigationViewModel navigationViewModel,
                                               ViewManagerModel viewManagerModel,
                                               PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
                                               IDSearchDataAccessInterface idSearchDataAccessInterface) {

        PlayerComparisonController playerComparisonController = createPlayerComparisonController(
                playerComparisonViewModel, navigationViewModel, viewManagerModel, playerComparisonDataAccessInterface, idSearchDataAccessInterface);

        return new PlayerComparisonView(playerComparisonController, playerComparisonViewModel);

    }

    private static PlayerComparisonController createPlayerComparisonController(
            PlayerComparisonViewModel playerComparisonViewModel,
            NavigationViewModel navigationViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
            IDSearchDataAccessInterface idSearchDataAccessInterface) {

        PlayerComparisonOutputBoundary playerComparisonOutputBoundary = new PlayerComparisonPresenter(
                playerComparisonViewModel, navigationViewModel, viewManagerModel);

        PlayerComparisonInputBoundary playerComparisonInputBoundary = new PlayerComparisonInteractor(
                playerComparisonDataAccessInterface, playerComparisonOutputBoundary, idSearchDataAccessInterface);

        return new PlayerComparisonController(playerComparisonInputBoundary);
    }

}
public class PlayerSearchUseCaseFactory {
    private PlayerSearchUseCaseFactory(){}

    public static PlayerSearchView create (ViewManagerModel viewManagerModel,
                                           NavigationViewModel navigationViewModel,
                                           PlayerSearchViewModel playerSearchViewModel,
                                           PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                           IDSearchDataAccessInterface idSearchDataAccessInterface) {

        PlayerSearchController playerSearchController = createPlayerSearchController(
                viewManagerModel, navigationViewModel, playerSearchViewModel, playerSearchDataAccessInterface, idSearchDataAccessInterface);

        return new PlayerSearchView(playerSearchController, playerSearchViewModel);
    }

    private static PlayerSearchController createPlayerSearchController(
            ViewManagerModel viewManagerModel,
            NavigationViewModel navigationViewModel,
            PlayerSearchViewModel playerSearchViewModel,
            PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
            IDSearchDataAccessInterface idSearchDataAccessInterface) {

        PlayerSearchOutputBoundary playerSearchOutputBoundary = new PlayerSearchPresenter(
                playerSearchViewModel, navigationViewModel, viewManagerModel);

        PlayerSearchInputDataBoundary playerSearchInputDataBoundary = new PlayerSearchInteractor(
                playerSearchDataAccessInterface, playerSearchOutputBoundary, idSearchDataAccessInterface);

        return new PlayerSearchController(playerSearchInputDataBoundary);
    }
}
public class IDSearchUseCaseFactory {

    private IDSearchUseCaseFactory(){};
    public static IDSearchView create(ViewManagerModel viewManagerModel,
                                      IDSearchViewModel idSearchViewModel,
                                      IDSearchDataAccessInterface idSearchDataAccessObject,
                                      NavigationViewModel navigationViewModel) {

        IDSearchController idSearchController = createIDSearchCase(
                viewManagerModel, idSearchViewModel, idSearchDataAccessObject, navigationViewModel);

        return new IDSearchView(idSearchController, idSearchViewModel);
    }

    private static IDSearchController createIDSearchCase(
            ViewManagerModel viewManagerModel,
            IDSearchViewModel idSearchViewModel,
            IDSearchDataAccessInterface idFileDataAccessObject,
            NavigationViewModel navigationViewModel){

        IDSearchOutputBoundary idSearchOutputBoundary= new IDSearchPresenter(
                viewManagerModel, idSearchViewModel, navigationViewModel);

        IDSearchInputBoundary idSearchInteractor = new IDSearchInteractor(
                idSearchOutputBoundary, idFileDataAccessObject);

        return new IDSearchController(idSearchInteractor);
    }


}