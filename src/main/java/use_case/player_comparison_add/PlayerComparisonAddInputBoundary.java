package main.java.use_case.player_comparison_add;

/**
 * Input interface for the player comparison add use case
 * contains execute methods which takes in input data for the use case
 */
public interface PlayerComparisonAddInputBoundary {

    /**
     * execute method to be called by the related interactor with the given input data
     */
    void execute(PlayerComparisonAddInputData playerComparisonAddInputData);

}
