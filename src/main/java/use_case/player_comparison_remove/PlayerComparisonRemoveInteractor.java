package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

/**
 * Interactor for the player comparison remove use case
 * Attempts to remove all player objects from the player comparison
 */
public class PlayerComparisonRemoveInteractor implements PlayerComparisonRemoveInputBoundary{

    final PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface;
    final PlayerComparisonRemoveOutputBoundary playerComparisonRemovePresenter;

    /**
     * Constructor for the class
     * @param playerComparisonRemoveDataAccessInterface object used to access stored information on player comparison
     * @param playerComparisonRemoveOutputBoundary object used to pass the output information from this interactor
     */
    public PlayerComparisonRemoveInteractor(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        this.playerComparisonRemoveDataAccessInterface = playerComparisonRemoveDataAccessInterface;
        this.playerComparisonRemovePresenter = playerComparisonRemoveOutputBoundary;
    }

    /**
     * Attempts to remove all players from the player comparison
     */
    public void execute() {
        if (playerComparisonRemoveDataAccessInterface.getSize() == 0){
            String error = "No players in the comparison";
            playerComparisonRemovePresenter.prepareFailView(error);
        }else{
            List<String> players = playerComparisonRemoveDataAccessInterface.removedPlayers();
            PlayerComparisonRemoveOutputData playerComparisonRemoveOutputData
                    = new PlayerComparisonRemoveOutputData(players);
            playerComparisonRemovePresenter.prepareSuccessView(playerComparisonRemoveOutputData);
        }

    }
}
