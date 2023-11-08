package main.java.app;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigation.NavigationViewModel;
import view.MainMenuView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {

        JFrame application = new JFrame("Main Menu");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout();

        // view objects
        JPanel views = new JPanel(gridLayout);
        application.add(views);

        // what view is showing
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, gridLayout, viewManagerModel);

        NavigationViewModel navigationViewModel = new NavigationViewModel();

        MainMenuView mainMenuView = NavigationUseCaseFactory.create(viewManagerModel, navigationViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}