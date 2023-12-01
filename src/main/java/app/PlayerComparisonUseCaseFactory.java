package main.java.app;

import main.java.entity.PlayerFactory;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.*;
import main.java.interface_adapter.player_comparison_add.*;
import main.java.interface_adapter.player_comparison_remove.*;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveOutputBoundary;
import main.java.use_case.player_comparison.*;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInteractor;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;
import main.java.view.PlayerComparisonView;

public class PlayerComparisonUseCaseFactory {

    private PlayerComparisonUseCaseFactory(){}

    public static PlayerComparisonView create (
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel navigationViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary,
            PlayerFactory playerFactory,
            PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface,
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonAddOutputBoundary playerComparisonAddOutputBoundary
    ) {
        PlayerComparisonAddController playerComparisonAddController = createPlayerComparisonAddController(playerComparisonAddDataAccessInterface, playerComparisonAddOutputBoundary);
        PlayerComparisonRemoveController playerComparisonRemoveController = createPlayerComparisonRemoveController(playerComparisonRemoveDataAccessInterface, playerComparisonRemoveOutputBoundary);
        PlayerComparisonController playerComparisonController = createPlayerComparisonController(
                playerComparisonViewModel, navigationViewModel, viewManagerModel, playerComparisonDataAccessInterface, playerFactory);

        return new PlayerComparisonView(playerComparisonController, playerComparisonAddController, playerComparisonRemoveController, playerComparisonViewModel);
    }

    private static PlayerComparisonController createPlayerComparisonController(
            PlayerComparisonViewModel playerComparisonViewModel,
            MainMenuViewModel navigationViewModel,
            ViewManagerModel viewManagerModel,
            PlayerComparisonDataAccessInterface playerComparisonDataAccessInterface,
            PlayerFactory playerFactory
    ) {
        PlayerComparisonOutputBoundary playerComparisonOutputBoundary = new PlayerComparisonPresenter(
                playerComparisonViewModel, navigationViewModel, viewManagerModel);

        PlayerComparisonInputBoundary playerComparisonInputBoundary = new PlayerComparisonInteractor(
                playerComparisonDataAccessInterface, playerComparisonOutputBoundary, playerFactory);

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
        return new PlayerComparisonRemoveController(removeInteractor, playerComparisonRemoveOutputBoundary);
    }

}

