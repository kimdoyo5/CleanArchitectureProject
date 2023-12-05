package main.java.use_case.player_comparison;

/**
 * The output boundary interface for the player comparison use case.
 * This interface defines methods for preparing views based on the results
 * of the player comparison operation. Implementing classes will handle
 * the presentation logic, such as updating the view with data or error messages.
 */
public interface PlayerComparisonOutputBoundary {

    /**
     * Prepares the view for a successful player comparison.
     * This method is called when the player comparison operation is successful,
     * and it provides the data needed to update the view.
     *
     * @param dataArray The data resulting from the player comparison.
     */
    void prepareSuccessView(PlayerComparisonOutputData dataArray);

    /**
     * Prepares the view for a failed player comparison.
     * This method is called when the player comparison operation fails,
     * and it provides the error message to be displayed.
     *
     * @param error The error message resulting from a failed comparison.
     */
    void prepareFailView(String error);

    /**
     * Handles the request to go back from the player comparison view.
     * This method triggers the process to return to the main menu or previous view.
     */
    void back();
}
