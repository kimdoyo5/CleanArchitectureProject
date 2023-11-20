package main.java.interface_adapter.id_search;

import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.id_search.IDSearchState;

import java.util.Map;

public class IDSearchPresenter implements IDSearchOutputBoundary {
    private final IDSearchViewModel idSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private NavigationViewModel navigationViewModel;

    public IDSearchPresenter(ViewManagerModel viewManagerModel,
                          IDSearchViewModel idSearchViewModel, NavigationViewModel navigationViewModel) {
        this.idSearchViewModel = idSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.navigationViewModel = navigationViewModel;
    }

    @Override
    public void prepareSuccessView(Map<String, Integer> results){
        IDSearchState idSearchState = idSearchViewModel.getState();
        idSearchState.setResult(results);
        this.idSearchViewModel.setState(idSearchState);
        idSearchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String str){
        IDSearchState idSearchState = idSearchViewModel.getState();
        idSearchState.setSearchError(str);
        this.idSearchViewModel.setState(idSearchState);
        idSearchViewModel.firePropertyChanged();
    }

    @Override
    public void back(){
        viewManagerModel.setActiveView(navigationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
