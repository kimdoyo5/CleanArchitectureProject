import main.java.app.Main;
import main.java.app.PlayerDataDisplayViewFactory;
import main.java.app.PlayerSearchUseCaseFactory;
import main.java.data_access.APIDataAccessObject;
import main.java.data_access.IDFileDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.NavigationViewModel;
import main.java.interface_adapter.player_search.PlayerSearchController;
import main.java.interface_adapter.player_search.PlayerSearchPresenter;
import main.java.interface_adapter.player_search.PlayerSearchState;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchInputData;
import main.java.use_case.player_search.PlayerSearchInteractor;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;
import main.java.view.PlayerDataDisplayView;
import main.java.view.PlayerSearchView;

import javax.swing.*;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;
public class PlayerSearchTest {

    PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
    PlayerSearchDataAccessInterface dataAccess = new APIDataAccessObject(new CommonPlayerFactory());
    PlayerDataDisplayViewModel playerDataDisplayViewModel = new PlayerDataDisplayViewModel();
    NavigationViewModel navigationViewModel = new NavigationViewModel();
    IDSearchDataAccessInterface idDataAccess = new IDFileDataAccessObject("database.csv");
    ViewManagerModel viewManagerModel = new ViewManagerModel();

    PlayerSearchOutputBoundary playerSearchPresenter = new PlayerSearchPresenter(playerSearchViewModel, navigationViewModel, viewManagerModel, playerDataDisplayViewModel);
    PlayerSearchInteractor playerSearchInteractor = new PlayerSearchInteractor(dataAccess, playerSearchPresenter, idDataAccess);
    PlayerSearchController playerSearchController = new PlayerSearchController(playerSearchInteractor);
    JPanel playerSearchView = new PlayerSearchView(playerSearchController, playerSearchViewModel);
    PlayerDataDisplayView playerDataDisplayView = PlayerDataDisplayViewFactory.create(playerDataDisplayViewModel,viewManagerModel,playerSearchViewModel);



    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @org.junit.Test
    public void searchById() throws IOException {
        playerSearchController.execute(641541);
        assert(Objects.equals(viewManagerModel.getActiveView(), playerDataDisplayViewModel.getViewName()));
    }

    @org.junit.Test
    public void invalidInput() throws IOException{
        PlayerSearchInputData invalidInput = new PlayerSearchInputData(10);
        playerSearchInteractor.execute(invalidInput);
        assert(Objects.equals(playerSearchInteractor.getError(), "no id"));
    }

    @org.junit.Test
    public void backExecute(){
        playerSearchController.execute();
        assert (viewManagerModel.getActiveView().equals(navigationViewModel.getViewName()));
    }


}
