package main.java.app;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;
import main.java.view.PlayerDataDisplayView;

public class PlayerDataDisplayViewFactory {
    private PlayerDataDisplayViewFactory(){}

    public static PlayerDataDisplayView create(PlayerDataDisplayViewModel playerDataDisplayViewModel, ViewManagerModel viewManagerModel, PlayerSearchViewModel playerSearchViewModel, PlayerComparisonAddViewModel playerComparisonAddViewModel, PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface){

        PlayerComparisonAddController playerComparisonAddController = PlayerComparisonAddUseCaseFactory.createPlayerComparisonAddController(playerComparisonAddViewModel, playerComparisonAddDataAccessInterface);
        return new PlayerDataDisplayView(playerDataDisplayViewModel, playerSearchViewModel, viewManagerModel, playerComparisonAddController, playerComparisonAddViewModel);

    }

}
