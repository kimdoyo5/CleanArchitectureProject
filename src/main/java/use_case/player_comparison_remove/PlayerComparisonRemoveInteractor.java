package main.java.use_case.player_comparison_remove;

import main.java.entity.Player;

import java.util.List;

public class PlayerComparisonRemoveInteractor implements PlayerComparisonRemoveInputBoundary{

    final PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface;
    final PlayerComparisonRemoveOutputBoundary playerComparisonRemovePresenter;

    public PlayerComparisonRemoveInteractor(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        this.playerComparisonRemoveDataAccessInterface = playerComparisonRemoveDataAccessInterface;
        this.playerComparisonRemovePresenter = playerComparisonRemoveOutputBoundary;
    }

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
