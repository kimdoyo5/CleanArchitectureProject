package main.java.app;

import interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.view.MainMenuView;
import view.ViewManager;
import main.java.app.NavigationUseCaseFactory;
import main.java.view.PlayerComparisonView;
import main.java.view.IDSearchView;
import main.java.view.PlayerSearchView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main{
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
        PlayerComparisonViewModel compareViewModel = new PlayerComparisonViewModel();
        IDSearchViewModel IDsearchViewModel = new IDSearchViewModel();
        PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();

        MainMenuView mainMenuView = NavigationUseCaseFactory.create(viewManagerModel, navigationViewModel,
                compareViewModel, IDsearchViewModel, playerSearchViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        /* to be added as UI for each use case is done
        CompareView compareView = CompareUseCaseFactory.create(viewManagerModel, navigationViewModel, compareViewModel);
        views.add(compareView, compareView.viewName);

        IDSearchView IDsearchView = IDSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, IDsearchViewModel);
        views.add(IDsearchView, IDsearchView.viewName);

        PlayerSearchView playerSearchView = PlayerSearchUseCaseFactory.create(viewManagerModel, navigationViewModel, PlayerSearchViewModel);
        views.add(playerSearchView, playerSearchView.viewName);
        */
        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
    }
}