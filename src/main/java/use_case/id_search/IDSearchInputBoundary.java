package main.java.use_case.id_search;

/**
 * Takes in an object of type IDSearchInputData and searches for the ID
 * specified. Also, can change the view from ID search to main menu.
 */
public interface IDSearchInputBoundary {
    /**
     * Searches for the ID of the player specified in the parameter
     * @param idSearchInputData the information about the player to search for
     */
    void execute(IDSearchInputData idSearchInputData);

    /**
     * Changes the view back to the main menu, called when the back button
     * is pressed
     */
    void execute();
}
