package main.java.interface_adapter.player_comparison_remove;

import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInputBoundary;

/**
 * Controller for removing players from the comparison use case that calls the interactor to complete the removal
 */
public class PlayerComparisonRemoveController {

    final PlayerComparisonRemoveInputBoundary playerComparisonRemoveUseCaseInteractor;

    /**
     * Constructor for the controller
     * @param playerComparisonRemoverUseCaseInteractor the related interactor for the player comparison remove use case
     */
    public PlayerComparisonRemoveController(
            PlayerComparisonRemoveInputBoundary playerComparisonRemoverUseCaseInteractor) {
        this.playerComparisonRemoveUseCaseInteractor = playerComparisonRemoverUseCaseInteractor;
    }

    /**
     * Calls the related interactor to remove all players from the comparison
     */
    public void execute() {
        playerComparisonRemoveUseCaseInteractor.execute();
    }

}
