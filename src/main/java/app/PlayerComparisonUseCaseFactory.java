package main.java.app;

import main.java.entity.PlayerFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonPresenter;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveViewModel;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonDataAccessInterface;
import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;
import main.java.use_case.player_comparison.PlayerComparisonInteractor;
import main.java.use_case.player_comparison.PlayerComparisonOutputBoundary;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInteractor;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;
import main.java.view.PlayerComparisonView;

public class PlayerComparisonUseCaseFactory {

    private PlayerComparisonUseCaseFactory(){}

    public static PlayerComparisonView create (
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel mainMenuViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary,
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel
    ) {
        PlayerComparisonController playerComparisonController = createPlayerComparisonController(
                playerComparisonViewModel, mainMenuViewModel, viewManagerModel, playerComparisonDataAccessInterface);
        PlayerComparisonRemoveController playerComparisonRemoveController = createPlayerComparisonRemoveController(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);


        return new PlayerComparisonView(playerComparisonController, playerComparisonRemoveController, playerComparisonViewModel, playerComparisonRemoveViewModel);
    }

    private static PlayerComparisonController createPlayerComparisonController(
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel navigationViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface
    ) {
        PlayerComparisonOutputBoundary playerComparisonOutputBoundary = new PlayerComparisonPresenter(
                playerComparisonViewModel, navigationViewModel, viewManagerModel);

        PlayerComparisonInputBoundary playerComparisonInputBoundary = new PlayerComparisonInteractor(
                playerComparisonDataAccessInterface, playerComparisonOutputBoundary);

        return new PlayerComparisonController(playerComparisonInputBoundary);
    }

    private static PlayerComparisonAddController createPlayerComparisonAddController(
            PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface,
            PlayerComparisonAddOutputBoundary playerComparisonAddOutputBoundary
    ) {
        PlayerComparisonAddInteractor addInteractor = new PlayerComparisonAddInteractor(playerComparisonAddDataAccessInterface, playerComparisonAddOutputBoundary);
        return new PlayerComparisonAddController(addInteractor);
    }

    private static PlayerComparisonRemoveController createPlayerComparisonRemoveController(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        PlayerComparisonRemoveInteractor removeInteractor = new PlayerComparisonRemoveInteractor(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);
        return new PlayerComparisonRemoveController(removeInteractor);
    }

}

