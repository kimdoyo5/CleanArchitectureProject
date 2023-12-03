package main.java.app;

import main.java.data_access.APIDataAccessObject;
import main.java.data_access.DatabaseDataAccessObject;
import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.entity.CommonPlayerFactory;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.data_access.IDFileDataAccessObject;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import main.java.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        PlayerComparisonViewModel compareViewModel = new PlayerComparisonViewModel();
        IDSearchViewModel IDsearchViewModel = new IDSearchViewModel();
        PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
        IDFileDataAccessObject idSearchDataAccessObject;
        PlayerComparisonDataAccessObject playerComparisonDataAccessObject;

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject(new CommonPlayerFactory());

        PlayerDataDisplayViewModel playerDataDisplayViewModel = new PlayerDataDisplayViewModel();
        PlayerComparisonAddViewModel playerComparisonAddViewModel = new PlayerComparisonAddViewModel();

        try {
            idSearchDataAccessObject = new IDFileDataAccessObject("./database.csv");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        try {
            playerComparisonDataAccessObject = new PlayerComparisonDataAccessObject("./playercomparison.csv", new CommonPlayerFactory());
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        MainMenuView mainMenuView = MainMenuViewFactory.create(viewManagerModel, mainMenuViewModel,
                compareViewModel, IDsearchViewModel, playerSearchViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        IDSearchView IDsearchView = IDSearchUseCaseFactory.create(viewManagerModel, IDsearchViewModel, idSearchDataAccessObject, mainMenuViewModel);
        views.add(IDsearchView, IDsearchView.viewName);

        /* to be added as UI for each use case is done
        CompareView compareView = CompareUseCaseFactory.create(viewManagerModel, navigationViewModel, compareViewModel);
        views.add(compareView, compareView.viewName);


        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessOject);
        views.add(playerSearchView, playerSearchView.viewName);
        */


        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, mainMenuViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessObject, playerDataDisplayViewModel);
        views.add(playerSearchView, playerSearchView.viewName);

        PlayerDataDisplayView playerDataDisplayView = PlayerDataDisplayViewFactory.create(playerDataDisplayViewModel, viewManagerModel, playerSearchViewModel, playerComparisonAddViewModel, playerComparisonDataAccessObject);
        views.add(playerDataDisplayView, playerDataDisplayView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
        DatabaseCreationInterface databaseCreation = new DatabaseDataAccessObject();
        databaseCreation.create();
    }
}