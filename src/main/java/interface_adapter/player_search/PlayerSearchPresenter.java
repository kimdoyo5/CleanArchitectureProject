package main.java.interface_adapter.player_search;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.use_case.player_search.PlayerOutputData;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;

import java.util.HashMap;
import java.util.Map;

public class PlayerSearchPresenter implements PlayerSearchOutputBoundary {
    private final PlayerSearchViewModel playerSearchViewModel;
    private final NavigationViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlayerSearchPresenter(PlayerSearchViewModel playerSearchViewModel, NavigationViewModel navigationViewModel, ViewManagerModel viewManagerModel){
        this.playerSearchViewModel = playerSearchViewModel;
        this.navigationViewModel = navigationViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(PlayerOutputData player) {
        Map<String, String> data = new HashMap<>();
        data.put("Name", player.getName());
        data.put("Player Id", String.valueOf(player.getID()));
        String[] stat_key = {"HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS"};
        for (String key: player.getStats().keySet()){
            data.put(key, player.getStats().get(key));
        }

        for (String key: stat_key){
            data.put(key, player.calculateStat(key));
        }
        PlayerSearchState state = playerSearchViewModel.getSearchState();
        state.setResult(data);
        this.playerSearchViewModel.setSearchState(state);
        playerSearchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PlayerSearchState state = playerSearchViewModel.getSearchState();
        state.setSearch_error(error);
        this.playerSearchViewModel.setSearchState(state);
        playerSearchViewModel.firePropertyChanged();
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
