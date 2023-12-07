package main.java.app;

import main.java.interface_adapter.ViewManagerModel;

import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.view.MainMenuView;


import javax.swing.*;
import java.io.IOException;

/**
 * Factory for the main menu view
 */
public class MainMenuViewFactory {
    private MainMenuViewFactory() {}

    /**
     * Creates the view
     * @param viewManagerModel view manager model
     * @param mainMenuViewModel The main menu view model
     * @param compareViewModel The view model for compare view
     * @param IDsearchViewModel the view model for id search
     * @param playerSearchViewModel the view model for player search
     * @return The created mainMenuView
     */
    public static MainMenuView create(
            ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, PlayerComparisonViewModel compareViewModel, IDSearchViewModel IDsearchViewModel,
            PlayerSearchViewModel playerSearchViewModel) {

        try {
            return new MainMenuView(mainMenuViewModel, viewManagerModel, playerSearchViewModel, compareViewModel, IDsearchViewModel);
        } catch (IOException e) {
            /// change
            JOptionPane.showMessageDialog(null, "Error");
        }

        return null;
    }





}
