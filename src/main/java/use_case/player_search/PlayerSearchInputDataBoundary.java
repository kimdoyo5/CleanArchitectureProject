package main.java.use_case.player_search;

import java.io.IOException;

/**
 * Takes the input data and searches for the result based on the input
 * contains execute method with the input data that searches for the result based on the user input
 * contains execute with no parameter which is used to go back to the main menu
 */
public interface PlayerSearchInputDataBoundary {
    /**
     * searches for the data based on the input and runs the output boundary to display to user
     * @param playerSearchInputData the input of the user
     */
    void execute(PlayerSearchInputData playerSearchInputData);
    void execute();
}
