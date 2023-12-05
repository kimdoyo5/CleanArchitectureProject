package main.java.use_case.player_search;

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
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.player_search.*;
import main.java.view.PlayerDataDisplayView;
import main.java.view.PlayerSearchView;

import javax.swing.*;

import java.io.IOException;
import java.util.Objects;

public class PlayerSearchTest {

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

    public PlayerSearchTest() throws IOException {
    }


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
        PlayerSearchOutputBoundary present = new PlayerSearchOutputBoundary(){
            @Override
            public void prepareSuccessView(PlayerOutputData player) {
                System.out.println("Wrong View");
                assert (false);
            }

            @Override
            public void prepareFailView(String error) {
                assert (error.equals("no id"));
            }

            @Override
            public void back() {
                assert (false);
            }

        };
        PlayerSearchInteractor playerSearchInteractor1 = new PlayerSearchInteractor(dataAccess ,present, idDataAccess);
        playerSearchInteractor1.execute(invalidInput);
    }

    @org.junit.Test
    public void backExecute(){
        playerSearchController.execute();
        assert (viewManagerModel.getActiveView().equals(mainMenuViewModel.getViewName()));
    }

    @org.junit.Test
    public void searchError(){
        PlayerSearchInputData validInput = new PlayerSearchInputData(641541);
        PlayerSearchOutputBoundary present = new PlayerSearchOutputBoundary(){
            @Override
            public void prepareSuccessView(PlayerOutputData player) {
                System.out.println("Wrong View");
                assert (false);
            }

            @Override
            public void prepareFailView(String error) {
                assert (error.equals("Search Error"));
            }

            @Override
            public void back() {
                assert (false);
            }

        };
        PlayerSearchDataAccessInterface localData = new PlayerSearchDataAccessInterface() {
            @Override
            public Player search(int player_id) throws IOException {
                IOException IOException = new IOException();
                throw IOException;
            }
        };
        PlayerSearchInteractor playerSearchInteractor1 = new PlayerSearchInteractor(localData ,present, idDataAccess);
        playerSearchInteractor1.execute(validInput);
    }


}
