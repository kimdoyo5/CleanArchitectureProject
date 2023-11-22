package main.java.use_case.player_compaison_remove;

import main.java.entity.Player;

public class PlayerComparisonRemoveInteractor implements PlayerComparisonRemoveInputBoundary{

    final PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface;
    final PlayerComparisonRemoveOutputBoundary playerComparisonRemovePresenter;

    public PlayerComparisonRemoveInteractor(
            PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface,
            PlayerComparisonRemoveOutputBoundary playerComparisonRemoveOutputBoundary) {
        this.playerComparisonRemoveDataAccessInterface = playerComparisonRemoveDataAccessInterface;
        this.playerComparisonRemovePresenter = playerComparisonRemoveOutputBoundary;
    }

    public void execute(PlayerComparisonRemoveInputData playerComparisonRemoveInputData) {
        int playerId = playerComparisonRemoveInputData.getPlayerId();
        Player removed = playerComparisonRemoveDataAccessInterface.remove(playerId);
        PlayerComparisonRemoveOutputData playerComparisonRemoveOutputData
                = new PlayerComparisonRemoveOutputData(removed);
        playerComparisonRemovePresenter.prepareSuccessView(playerComparisonRemoveOutputData);
    }
}
