package main.java.use_case.player_comparison;

/**
 * Interface defining the input boundary for player comparison.
 * This interface represents the contract for the input side of the player comparison use case,
 * allowing for interaction with the player comparison logic from the controller layer.
 */
public interface PlayerComparisonInputBoundary {

    /**
     * Executes the main logic of player comparison.
     * This method should be called to start the process of comparing players
     * based on their statistics or other criteria.
     */
    void execute();

    /**
     * Handles the request to go back from the player comparison process.
     * This method should be invoked when the user wants to exit the player
     * comparison view or process and return to the previous state.
     */
    void back();
}

