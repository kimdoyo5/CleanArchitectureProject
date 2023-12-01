package main.java.interface_adapter.id_search;

import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.interface_adapter.ViewManagerModel;

import java.util.Map;

public class IDSearchPresenter implements IDSearchOutputBoundary {
    private final IDSearchViewModel idSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private MainMenuViewModel mainMenuViewModel;

    public IDSearchPresenter(ViewManagerModel viewManagerModel,
                          IDSearchViewModel idSearchViewModel, MainMenuViewModel mainMenuViewModel) {
        this.idSearchViewModel = idSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
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
        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
