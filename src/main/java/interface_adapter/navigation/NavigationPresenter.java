package main.java.interface_adapter.navigation;

import main.java.interface_adapter.compare.CompareState;
import main.java.use_case.navigation.NavigationOutputBoundary;
import main.java.interface_adapter.compare.CompareViewModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import interface_adapter.ViewManagerModel;


public class NavigationPresenter implements NavigationOutputBoundary {
    private final NavigationViewModel navigationViewModel;
    private final CompareViewModel compareViewModel;
    private final PlayerSearchViewModel playerSearchViewModel;
    private final IDSearchViewModel IDsearchViewModel;
    private ViewManagerModel viewManagerModel;

    public NavigationPresenter(interface_adapter.ViewManagerModel viewManagerModel,
                           NavigationViewModel navigationViewModel, CompareViewModel compareViewModel,
                               PlayerSearchViewModel playerSearchViewModel, IDSearchViewModel idSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.navigationViewModel = navigationViewModel;
        this.compareViewModel = compareViewModel;
        this.playerSearchViewModel = playerSearchViewModel;
        this.IDsearchViewModel = idSearchViewModel;
    }

    @Override
    public void prepareCompareView() {
        viewManagerModel.setActiveView(compareViewModel.getViewName());
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
