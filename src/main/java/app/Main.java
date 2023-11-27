package main.java.app;

import main.java.data_access.APIDataAccessObject;
import main.java.data_access.DatabaseDataAccessObject;
import main.java.entity.CommonPlayerFactory;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.data_access.IDFileDataAccessObject;
import main.java.interface_adapter.compare.CompareViewModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import main.java.view.*;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args) {

        JFrame application = new JFrame("Main Menu");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // view objects
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // what view is showing
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        NavigationViewModel navigationViewModel = new NavigationViewModel();
        CompareViewModel compareViewModel = new CompareViewModel();
        IDSearchViewModel IDsearchViewModel = new IDSearchViewModel();
        PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
        IDFileDataAccessObject idSearchDataAccessOject;

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject(new CommonPlayerFactory());

        PlayerDataDisplayViewModel playerDataDisplayViewModel = new PlayerDataDisplayViewModel();

        try {
            idSearchDataAccessOject = new IDFileDataAccessObject("./database.csv");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        MainMenuView mainMenuView = NavigationUseCaseFactory.create(viewManagerModel, navigationViewModel,
                compareViewModel, IDsearchViewModel, playerSearchViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        IDSearchView IDsearchView = IDSearchUseCaseFactory.create(viewManagerModel, IDsearchViewModel, idSearchDataAccessOject, navigationViewModel);
        views.add(IDsearchView, IDsearchView.viewName);

        /* to be added as UI for each use case is done
        CompareView compareView = CompareUseCaseFactory.create(viewManagerModel, navigationViewModel, compareViewModel);
        views.add(compareView, compareView.viewName);


        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessOject);
        views.add(playerSearchView, playerSearchView.viewName);
        */


        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessOject, playerDataDisplayViewModel);
        views.add(playerSearchView, playerSearchView.viewName);

        PlayerDataDisplayView playerDataDisplayView = PlayerDataDisplayViewFactory.create(playerDataDisplayViewModel, viewManagerModel, playerSearchViewModel);
        views.add(playerDataDisplayView, playerDataDisplayView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
        DatabaseCreationInterface databaseCreation = new DatabaseDataAccessObject();
        databaseCreation.create();
    }
}