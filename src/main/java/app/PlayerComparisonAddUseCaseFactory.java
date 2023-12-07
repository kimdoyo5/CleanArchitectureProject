package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddPresenter;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInputBoundary;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInteractor;
import main.java.use_case.player_comparison_add.PlayerComparisonAddOutputBoundary;

import java.io.IOException;

/**
 * Factory for creating the required classes for player comparison add
 */
public class PlayerComparisonAddUseCaseFactory {

    private PlayerComparisonAddUseCaseFactory(){}

    /**
     * Creates the controller for player comparison add
     * @param playerComparisonAddViewModel view model for player comparison
     * @param playerComparisonAddDataAccessInterface the data access object for player comparison
     * @return the created controller
     */
    public static PlayerComparisonAddController createPlayerComparisonAddController(
            PlayerComparisonAddViewModel playerComparisonAddViewModel,
            PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface){

        PlayerComparisonAddOutputBoundary playerComparisonAddOutputBoundary =
                new PlayerComparisonAddPresenter(playerComparisonAddViewModel);

        PlayerComparisonAddInputBoundary playerComparisonAddInputBoundary =
                new PlayerComparisonAddInteractor(
                        playerComparisonAddDataAccessInterface,
                        playerComparisonAddOutputBoundary);

        return new PlayerComparisonAddController(playerComparisonAddInputBoundary);

    }


}
