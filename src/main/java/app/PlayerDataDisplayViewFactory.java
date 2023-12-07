package main.java.app;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.view.PlayerDataDisplayView;

/**
 * Creates the view that displays the data after search
 */
public class PlayerDataDisplayViewFactory {
    private PlayerDataDisplayViewFactory(){}

    /**
     * Creates the view
     * @param playerDataDisplayViewModel view model of data display
     * @param viewManagerModel view manager model
     * @param playerSearchViewModel viewmodel for player search
     * @param playerComparisonAddViewModel viewmodel for adding player to comparison
     * @param playerComparisonAddDataAccessInterface the data access object for player comparison
     * @return the created view
     */
    public static PlayerDataDisplayView create(PlayerDataDisplayViewModel playerDataDisplayViewModel,
                                               ViewManagerModel viewManagerModel,
                                               PlayerSearchViewModel playerSearchViewModel,
                                               PlayerComparisonAddViewModel playerComparisonAddViewModel,
                                               PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface){

        PlayerComparisonAddController playerComparisonAddController = PlayerComparisonAddUseCaseFactory.createPlayerComparisonAddController(playerComparisonAddViewModel, playerComparisonAddDataAccessInterface);
        return new PlayerDataDisplayView(playerDataDisplayViewModel, playerSearchViewModel, viewManagerModel, playerComparisonAddController, playerComparisonAddViewModel);

    }

}
