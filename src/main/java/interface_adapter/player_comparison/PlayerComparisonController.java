package main.java.interface_adapter.player_comparison;

import main.java.use_case.player_comparison.PlayerComparisonInputBoundary;

/**
 * Controller class for player comparison.
 * This class acts as a controller in the MVC pattern, handling the user's interaction
 * with the player comparison feature. It communicates with the input boundary
 * of the player comparison use case.
 */
public class PlayerComparisonController {

    // Reference to the player comparison use case interactor
    final PlayerComparisonInputBoundary playerComparisonUseCaseInteractor;

    /**
     * Constructs a PlayerComparisonController with the specified input boundary.
     *
     * @param comparisonUseCaseInteractor The input boundary of the player comparison use case.
     */
    public PlayerComparisonController(PlayerComparisonInputBoundary comparisonUseCaseInteractor) {
        this.playerComparisonUseCaseInteractor = comparisonUseCaseInteractor;
    }

    /**
     * Initiates the execution of player comparison.
     * This method triggers the comparison process in the use case.
     */
    public void execute() {
        // Player comparison interactor
        playerComparisonUseCaseInteractor.execute();
    }

    /**
     * Handles the request to go back from the player comparison view.
     * This method triggers the process to return to the main menu or previous view.
     */
    public void back() {
        // Back to main menu
        playerComparisonUseCaseInteractor.back();
    }

}
