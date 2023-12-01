package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

public class PlayerComparisonAddInteractor implements PlayerComparisonAddInputBoundary {

    final PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface;
    final PlayerComparisonAddOutputBoundary playerComparisonAddPresenter;

    public PlayerComparisonAddInteractor(
            PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface,
            PlayerComparisonAddOutputBoundary playerComparisonAddOutputBoundary) {
        this.playerComparisonAddDataAccessInterface = playerComparisonAddDataAccessInterface;
        this.playerComparisonAddPresenter = playerComparisonAddOutputBoundary;
    }

    @Override
    public void execute(PlayerComparisonAddInputData playerComparisonAddInputData) {
        Player player = playerComparisonAddInputData.getPlayer();
        boolean added = playerComparisonAddDataAccessInterface.add(player); // PlayerComparisonDataAccessObject
        if (added) {
            PlayerComparisonAddOutputData playerComparisonAddOutputData = new PlayerComparisonAddOutputData(player);
            playerComparisonAddPresenter.prepareSuccessView(playerComparisonAddOutputData);
        } else {
            playerComparisonAddPresenter.prepareFailView(
                    "Max amount(4) of players already added to the player comparison");
        }

    }

}
