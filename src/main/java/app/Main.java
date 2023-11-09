package main.java.app;

import interface_adapter.ViewManagerModel;
import main.java.interface_adapter.compare.CompareViewModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.view.MainMenuView;
import view.ViewManager;
import main.java.app.NavigationUseCaseFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
// you are cringe
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
        CompareViewModel compareViewModel = new CompareViewModel();
        MainMenuView mainMenuView = NavigationUseCaseFactory.create(viewManagerModel, navigationViewModel, );
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}