package main.java.app;

import main.java.interface_adapter.ViewManagerModel;

import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.view.MainMenuView;


import javax.swing.*;
import java.io.IOException;

public class MainMenuViewFactory {
    private MainMenuViewFactory() {}

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
