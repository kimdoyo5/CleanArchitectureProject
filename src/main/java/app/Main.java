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
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemovePresenter;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
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
        PlayerComparisonViewModel playerComparisonViewModel = new PlayerComparisonViewModel();
        IDSearchViewModel IDsearchViewModel = new IDSearchViewModel();
        PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
        PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel = new PlayerComparisonRemoveViewModel();
        IDFileDataAccessObject idSearchDataAccessObject;
        PlayerComparisonDataAccessObject playerComparisonDataAccessObject;
        PlayerComparisonRemovePresenter playerComparisonRemovePresenter = new PlayerComparisonRemovePresenter(playerComparisonRemoveViewModel);

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
                playerComparisonViewModel, IDsearchViewModel, playerSearchViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        IDSearchView IDsearchView = IDSearchUseCaseFactory.create(viewManagerModel, IDsearchViewModel, idSearchDataAccessObject, mainMenuViewModel);
        views.add(IDsearchView, IDsearchView.viewName);

        PlayerComparisonView playerComparisonView = PlayerComparisonUseCaseFactory.create(playerComparisonViewModel, mainMenuViewModel, viewManagerModel, playerComparisonDataAccessObject, playerComparisonRemovePresenter, playerComparisonDataAccessObject, playerComparisonRemoveViewModel);
        views.add(playerComparisonView, playerComparisonView.viewName);

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