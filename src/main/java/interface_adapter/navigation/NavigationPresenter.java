package main.java.interface_adapter.navigation;

import main.java.interface_adapter.player_comparison.PlayerComparisonState;
import main.java.use_case.navigation.NavigationOutputBoundary;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.interface_adapter.ViewManagerModel;


public class NavigationPresenter implements NavigationOutputBoundary {
    private final NavigationViewModel navigationViewModel;
    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final PlayerSearchViewModel playerSearchViewModel;
    private final IDSearchViewModel IDsearchViewModel;
    private ViewManagerModel viewManagerModel;

    public NavigationPresenter(ViewManagerModel viewManagerModel,
                           NavigationViewModel navigationViewModel, PlayerComparisonViewModel playerComparisonViewModel,
                               PlayerSearchViewModel playerSearchViewModel, IDSearchViewModel idSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.navigationViewModel = navigationViewModel;
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.playerSearchViewModel = playerSearchViewModel;
        this.IDsearchViewModel = idSearchViewModel;
    }

    @Override
    public void preparePlayerComparisonView() {
        viewManagerModel.setActiveView(playerComparisonViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void preparePlayerSearchView() {
        viewManagerModel.setActiveView(playerSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareIDSearchView(){
        viewManagerModel.setActiveView(IDsearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
