package main.java.interface_adapter.player_comparison_add;

import main.java.use_case.player_comparison_add.PlayerComparisonAddInputBoundary;
import main.java.entity.Player;
import main.java.use_case.player_comparison_add.PlayerComparisonAddInputData;

/**
 * Controller for add players to the comparison use case that passes the required player data to the related interactor
 * and call the interactor to complete the add
 */
public class PlayerComparisonAddController {

    final PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor;

    /**
     * Constructor for the controller
     * @param playerComparisonAddUseCaseInteractor the related interactor for the player comparison add use case
     */
    public PlayerComparisonAddController(PlayerComparisonAddInputBoundary playerComparisonAddUseCaseInteractor) {
        this.playerComparisonAddUseCaseInteractor = playerComparisonAddUseCaseInteractor;
    }

    /**
     * Calls the related interactor to add the given player into the comparison
     * @param player The inputted player to be added to the comparison
     */
    public void execute(Player player) {
        PlayerComparisonAddInputData playerComparisonAddInputData = new PlayerComparisonAddInputData(player);
        playerComparisonAddUseCaseInteractor.execute(playerComparisonAddInputData);
    }

}
