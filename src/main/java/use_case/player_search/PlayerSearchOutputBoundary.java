package main.java.use_case.player_search;

/**
 * The output of the player search use case which displays the results of the user's input
 * Method prepareSuccessView displays the info that is found without error
 * Method prepareFailView displays the error that happened which searching
 * Method back returns to the main menu
 */
public interface PlayerSearchOutputBoundary {
    /**
     * Displays the result based on the input data
     * @param player the result of the search
     */
    void prepareSuccessView(PlayerOutputData player);

    /**
     * Displays the error that happened
     * @param error The cause of the error
     */
    void prepareFailView(String error);

    void back();
}
