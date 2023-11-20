package main.java.app;

import main.java.interface_adapter.id_search.IDSearchController;
import main.java.interface_adapter.id_search.IDSearchPresenter;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.id_search.IDSearchInputBoundary;
import main.java.use_case.id_search.IDSearchInteractor;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.view.IDSearchView;
import main.java.interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class IDSearchUseCaseFactory {

    private IDSearchUseCaseFactory(){};
    public static IDSearchView create(ViewManagerModel viewManagerModel, IDSearchViewModel idSearchViewModel, IDSearchDataAccessInterface idSearchDataAccessObject, NavigationViewModel navigationViewModel){
            IDSearchController idSearchController = createIDSearchCase(viewManagerModel, idSearchViewModel, idSearchDataAccessObject, navigationViewModel);
            return new IDSearchView(idSearchController, idSearchViewModel);
    }

    private static IDSearchController createIDSearchCase(ViewManagerModel viewManagerModel, IDSearchViewModel idSearchViewModel, IDSearchDataAccessInterface idFileDataAccessObject, NavigationViewModel navigationViewModel){

        IDSearchOutputBoundary idSearchOutputBoundary= new IDSearchPresenter(viewManagerModel, idSearchViewModel, navigationViewModel);

        IDSearchInputBoundary idSearchInteractor = new IDSearchInteractor( idSearchOutputBoundary, idFileDataAccessObject);

        return new IDSearchController(idSearchInteractor);
    }


}
