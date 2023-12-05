package main.java.interface_adapter.player_search;

import main.java.app.PlayerDataDisplayViewFactory;
import main.java.data_access.APIDataAccessObject;
import main.java.data_access.IDFileDataAccessObject;
import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.entity.Player;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchController;
import main.java.interface_adapter.player_search.PlayerSearchPresenter;
import main.java.interface_adapter.player_search.PlayerSearchState;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.player_search.*;
import main.java.view.PlayerDataDisplayView;
import main.java.view.PlayerSearchView;
import org.junit.Test;

import javax.swing.*;

import java.io.IOException;
import java.util.Objects;
public class PlayerSearchStateTest {
    PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
    PlayerSearchDataAccessInterface dataAccess = new APIDataAccessObject(new CommonPlayerFactory());
    PlayerDataDisplayViewModel playerDataDisplayViewModel = new PlayerDataDisplayViewModel();
    MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
    IDSearchDataAccessInterface idDataAccess = new IDFileDataAccessObject("database.csv");
    ViewManagerModel viewManagerModel = new ViewManagerModel();

    PlayerSearchOutputBoundary playerSearchPresenter = new PlayerSearchPresenter(playerSearchViewModel, mainMenuViewModel, viewManagerModel, playerDataDisplayViewModel);
    PlayerSearchInteractor playerSearchInteractor = new PlayerSearchInteractor(dataAccess, playerSearchPresenter, idDataAccess);
    PlayerSearchController playerSearchController = new PlayerSearchController(playerSearchInteractor);
    JPanel playerSearchView = new PlayerSearchView(playerSearchController, playerSearchViewModel);

    PlayerComparisonAddViewModel playerComparisonAddViewModel = new PlayerComparisonAddViewModel();

    PlayerComparisonDataAccessObject playerComparisonDataAccessObject = new PlayerComparisonDataAccessObject("playercomparison.csv", new CommonPlayerFactory());
    PlayerDataDisplayView playerDataDisplayView = PlayerDataDisplayViewFactory.create(playerDataDisplayViewModel,viewManagerModel,playerSearchViewModel, playerComparisonAddViewModel, playerComparisonDataAccessObject);

    PlayerSearchState playerSearchState = new PlayerSearchState();

    public PlayerSearchStateTest() throws IOException {
    }

    @org.junit.Test
    public void errorSet(){
        PlayerSearchInputData invalidInput = new PlayerSearchInputData(10);
        PlayerSearchOutputBoundary present = new PlayerSearchOutputBoundary(){
            @Override
            public void prepareSuccessView(PlayerOutputData player) {
                assert (false);
            }

            @Override
            public void prepareFailView(String error) {
                playerSearchState.setSearch_error(error);
            }

            @Override
            public void back() {
                assert (false);
            }

        };
        PlayerSearchInteractor playerSearchInteractor1 = new PlayerSearchInteractor(dataAccess ,present, idDataAccess);
        playerSearchInteractor1.execute(invalidInput);
        assert (playerSearchState.getSearch_error().equals("no id"));
    }

    @org.junit.Test
    public void cloneTest(){
        playerSearchState.setSearch("641541");
        playerSearchState.setSearch_error("no id");
        PlayerSearchState playerSearchStateClone = new PlayerSearchState(playerSearchState);
        assert (playerSearchStateClone.getSearch_error().equals(playerSearchState.getSearch_error()) && playerSearchStateClone.getSearch().equals(playerSearchState.getSearch()));

    }

}
