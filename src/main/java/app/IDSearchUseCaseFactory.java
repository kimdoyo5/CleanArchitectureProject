package main.java.app;

import main.java.interface_adapter.id_search.IDSearchController;
import main.java.interface_adapter.id_search.IDSearchPresenter;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.id_search.IDSearchInputBoundary;
import main.java.use_case.id_search.IDSearchInteractor;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.view.IDSearchView;
import main.java.interface_adapter.ViewManagerModel;

/**
 * Factory for the view of ID search use case
 */
public class IDSearchUseCaseFactory {

    private IDSearchUseCaseFactory(){};

    /**
     * Creates the view
     * @param viewManagerModel the view manager model
     * @param idSearchViewModel view model for id search
     * @param idSearchDataAccessObject the data access object used for id search
     * @param mainMenuViewModel the view model of the main menu
     * @return the created view
     */
    public static IDSearchView create(ViewManagerModel viewManagerModel, IDSearchViewModel idSearchViewModel, IDSearchDataAccessInterface idSearchDataAccessObject, MainMenuViewModel mainMenuViewModel){
            IDSearchController idSearchController = createIDSearchCase(viewManagerModel, idSearchViewModel, idSearchDataAccessObject, mainMenuViewModel);
            return new IDSearchView(idSearchController, idSearchViewModel);
    }

    private static IDSearchController createIDSearchCase(ViewManagerModel viewManagerModel, IDSearchViewModel idSearchViewModel, IDSearchDataAccessInterface idFileDataAccessObject, MainMenuViewModel mainMenuViewModel){

        IDSearchOutputBoundary idSearchOutputBoundary= new IDSearchPresenter(viewManagerModel, idSearchViewModel, mainMenuViewModel);

        IDSearchInputBoundary idSearchInteractor = new IDSearchInteractor( idSearchOutputBoundary, idFileDataAccessObject);

        return new IDSearchController(idSearchInteractor);
    }


}
