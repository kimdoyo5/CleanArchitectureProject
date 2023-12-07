package main.java.use_case.id_search;

import java.util.List;
import java.util.Map;

/**
 * The interface for interactor output, is implemented by the presenter
 */
public interface IDSearchOutputBoundary {
    /**
     * Behaviour for if there was an error. Shows the error to the user
     * @param error description of the cause of the error
     */
    void prepareFailView(String error);

    /**
     * Behaviour for if the search was successful. Shows search results
     * to user
     * @param result the possible players and IDs that correspond to the search
     */
    void prepareSuccessView(IDSearchOutputData result);

    /**
     * Switches the view to the main menu
     */
    void back();
}
