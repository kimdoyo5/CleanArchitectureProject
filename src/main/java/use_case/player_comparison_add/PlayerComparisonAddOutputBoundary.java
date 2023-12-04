package main.java.use_case.player_comparison_add;

/**
 * Output interface for the player comparison add use case
 * contains methods to prepare the view based on whether the player was successfully added to the comparison
 */
public interface PlayerComparisonAddOutputBoundary {

    /**
     * Prepares the success view for when the player is successfully added to the comparison
     * @param playerComparisonAddOutputData output data from the interactor
     */
    void prepareSuccessView(PlayerComparisonAddOutputData playerComparisonAddOutputData);

    /**
     * Prepares the fail view for when the player is not successfully added to the comparison
     * @param error string description of why the player was not added to the comparison
     */
    void prepareFailView(String error);

}
