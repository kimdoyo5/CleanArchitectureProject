package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

/**
 * Interactor for the player comparison add use case
 * Attempts to add inputted player objects to the player comparison
 */
public class PlayerComparisonAddInteractor implements PlayerComparisonAddInputBoundary {

    final PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface;
    final PlayerComparisonAddOutputBoundary playerComparisonAddPresenter;

    /**
     * Constructor for the class
     * @param playerComparisonAddDataAccessInterface object used to access stored information on player comparison
     * @param playerComparisonAddOutputBoundary object used to pass the output information from this interactor
     */
    public PlayerComparisonAddInteractor(
            PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface,
            PlayerComparisonAddOutputBoundary playerComparisonAddOutputBoundary) {
        this.playerComparisonAddDataAccessInterface = playerComparisonAddDataAccessInterface;
        this.playerComparisonAddPresenter = playerComparisonAddOutputBoundary;
    }

    /**
     * Attempts to add the inputted player object to the player comparison
     * @param playerComparisonAddInputData the input data passed into this interactor,
     * contains the player attempted to be added
     */
    @Override
    public void execute(PlayerComparisonAddInputData playerComparisonAddInputData) {
        Player player = playerComparisonAddInputData.getPlayer();
        boolean added = playerComparisonAddDataAccessInterface.add(player); // PlayerComparisonDataAccessObject
        if (added) {
            PlayerComparisonAddOutputData playerComparisonAddOutputData = new PlayerComparisonAddOutputData(player);
            playerComparisonAddPresenter.prepareSuccessView(playerComparisonAddOutputData);
        } else {
            String error;
            if (playerComparisonAddDataAccessInterface.getSize() >= 4){
                error = "Max amount(4) of players already added to the player comparison";
            }else{
                error = "Player already added to player comparison";
            }
            playerComparisonAddPresenter.prepareFailView(error);
        }

    }

}
