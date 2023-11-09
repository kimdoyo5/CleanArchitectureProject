package main.java.app;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigation.NavigationController;
import main.java.interface_adapter.compare.CompareViewModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.NavigationPresenter;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.navigation.NavigationInteractor;
import main.java.view.MainMenuView;
import main.java.use_case.navigation.NavigationInputBoundary;
import main.java.use_case.navigation.NavigationOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class NavigationUseCaseFactory {
    private NavigationUseCaseFactory() {}

    public static MainMenuView create(
            ViewManagerModel viewManagerModel, NavigationViewModel navigationViewModel, CompareViewModel compareViewModel, IDSearchViewModel IDsearchViewModel,
            PlayerSearchViewModel playerSearchViewModel) {

        try {
            NavigationController navigationController = createNavigationUseCase(viewManagerModel, navigationViewModel, compareViewModel, IDsearchViewModel, playerSearchViewModel);
            return new MainMenuView(navigationViewModel, navigationController);
        } catch (IOException e) {
            /// change
            JOptionPane.showMessageDialog(null, "Error");
        }

        return null;
    }


    private static NavigationController createNavigationUseCase(ViewManagerModel viewManagerModel,
                                                                NavigationViewModel navigationViewModel,
                                                                CompareViewModel compareViewModel, IDSearchViewModel IDsearchViewModel,
                                                                PlayerSearchViewModel playerSearchViewModel) throws IOException {


        NavigationOutputBoundary navigationOutputBoundary = new NavigationPresenter(viewManagerModel, navigationViewModel, compareViewModel, playerSearchViewModel, IDsearchViewModel);

        NavigationInputBoundary navigationInteractor = new NavigationInteractor(navigationOutputBoundary);

        return new NavigationController(navigationInteractor);
    }


}
