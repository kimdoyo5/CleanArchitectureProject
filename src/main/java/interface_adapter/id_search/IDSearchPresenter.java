package main.java.interface_adapter.id_search;

import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.id_search.IDSearchOutputData;

import java.util.Map;

/**
 * The presenter for ID search, takes the output data and updates the view or
 * displays the corresponding error
 */
public class IDSearchPresenter implements IDSearchOutputBoundary {
    private final IDSearchViewModel idSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private MainMenuViewModel mainMenuViewModel;

    /**
     * Initializes the presenter
     * @param viewManagerModel the view manager model
     * @param idSearchViewModel the id search view model
     * @param mainMenuViewModel the main menu view model
     */
    public IDSearchPresenter(ViewManagerModel viewManagerModel,
                             IDSearchViewModel idSearchViewModel, MainMenuViewModel mainMenuViewModel) {
        this.idSearchViewModel = idSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    /**
     * Behaviour for if the search was successful. Updates the view model state
     * to add the results to be displayed, passes the data that there was not
     * an error in the search, and calls firePropertyChanged() on the view model
     * to make these updates visible to the user
     * @param results the possible players and IDs that correspond to the search
     */
    @Override
    public void prepareSuccessView(IDSearchOutputData results){
        IDSearchState idSearchState = idSearchViewModel.getState();
        idSearchState.setResult(results.getPlayers());
        idSearchState.setSearchError(null);
        this.idSearchViewModel.setState(idSearchState);
        idSearchViewModel.firePropertyChanged();
    }

    /**
     * Behaviour for if there was an error in the search. Sets the search
     * error in the view model and calls firePropertyChanged() to alert the
     * view model to the change which will show it to the user
     * @param str description of the cause of the error
     */
    @Override
    public void prepareFailView(String str){
        IDSearchState idSearchState = idSearchViewModel.getState();
        idSearchState.setSearchError(str);
        this.idSearchViewModel.setState(idSearchState);
        idSearchViewModel.firePropertyChanged();
    }

    /**
     * Changes the view back to main manu
     */
    @Override
    public void back(){
        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
