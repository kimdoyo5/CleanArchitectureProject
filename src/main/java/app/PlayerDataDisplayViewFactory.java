package main.java.app;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.view.PlayerDataDisplayView;

public class PlayerDataDisplayViewFactory {
    private PlayerDataDisplayViewFactory(){}

    public static PlayerDataDisplayView create(PlayerDataDisplayViewModel playerDataDisplayViewModel, ViewManagerModel viewManagerModel, PlayerSearchViewModel playerSearchViewModel){

        return new PlayerDataDisplayView(playerDataDisplayViewModel, playerSearchViewModel, viewManagerModel);

    }

}
