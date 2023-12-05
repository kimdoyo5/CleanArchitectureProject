package main.java.use_case.player_comparison_remove;

/**
 * Output interface for the player comparison remove use case
 * contains methods to prepare the view based on whether the players were successfully remove from the comparison
 */
public interface PlayerComparisonRemoveOutputBoundary {

    /**
     * Prepares the success view for when the players are successfully removed from the comparison
     * @param playerComparisonRemoveOutputData output data from the interactor
     */
    void prepareSuccessView(PlayerComparisonRemoveOutputData playerComparisonRemoveOutputData);

    /**
     * Prepares the fail view for when there are already no players in the comparison
     * @param error string description of how there are already no players in the comparison
     */
    void prepareFailView(String error);

}
