package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigation.NavigationController;
import interface_adapter.navigation.NavigationPresenter;
import interface_adapter.navigation.NavigationViewModel;
import use_case.navigation.NavigationInteractor;
import view.MainMenuView;

import javax.swing.*;
import java.io.IOException;

public class NavigationUseCaseFactory {
    private NavigationUseCaseFactory() {}

    public static MainMenuView create(
            ViewManagerModel viewManagerModel, NavigationViewModel navigationViewModel) {

        try {
            NavigationController navigationController = createNavigationUseCase(viewManagerModel, navigationViewModel);
            return new MainMenuView(navigationViewModel, navigationController);
        } catch (IOException e) {
            /// change
            JOptionPane.showMessageDialog(null, "Error");
        }

        return null;
    }

    /*
    private static NavigationController createNavigationUseCase(ViewManagerModel viewManagerModel, NavigationViewModel navigationViewModel) throws IOException {


        NavigationOutputBoundry navigationOutputBoundry = new NavigationPresenter(viewManagerModel);

        NavigationInputBoundry navigationInteractor = new NavigationInteractor(navigationOutputBoundry);

        return new NavigationController(navigationInteractor);
    }
    */

}
