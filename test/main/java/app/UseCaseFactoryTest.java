package main.java.app;

import main.java.data_access.APIDataAccessObject;
import main.java.data_access.IDFileDataAccessObject;
import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemovePresenter;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;
import main.java.view.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCaseFactoryTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
    PlayerComparisonViewModel playerComparisonViewModel = new PlayerComparisonViewModel();
    IDSearchViewModel iDsearchViewModel = new IDSearchViewModel();
    PlayerSearchViewModel playerSearchViewModel = new PlayerSearchViewModel();
    PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel = new PlayerComparisonRemoveViewModel();
    IDFileDataAccessObject idSearchDataAccessObject;
    PlayerComparisonDataAccessObject playerComparisonDataAccessObject;
    PlayerComparisonRemovePresenter playerComparisonRemovePresenter = new PlayerComparisonRemovePresenter(playerComparisonRemoveViewModel);

    APIDataAccessObject apiDataAccessObject = new APIDataAccessObject(new CommonPlayerFactory());

    PlayerDataDisplayViewModel playerDataDisplayViewModel = new PlayerDataDisplayViewModel();
    PlayerComparisonAddViewModel playerComparisonAddViewModel = new PlayerComparisonAddViewModel();


    @BeforeEach
    public void setUp(){
        try {
            idSearchDataAccessObject = new IDFileDataAccessObject("./database.csv");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        try {
            playerComparisonDataAccessObject = new PlayerComparisonDataAccessObject("./playercomparison.csv", new CommonPlayerFactory());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void mainMenuFactory(){
        Object obj = MainMenuViewFactory.create(viewManagerModel, mainMenuViewModel, playerComparisonViewModel, iDsearchViewModel, playerSearchViewModel);
        assertTrue(obj instanceof MainMenuView);
    }

    @org.junit.Test
    public void playerSearchFactory(){
        Object obj = PlayerSearchUseCaseFactory.create(viewManagerModel, mainMenuViewModel, playerSearchViewModel, apiDataAccessObject, idSearchDataAccessObject, playerDataDisplayViewModel);
        assertTrue(obj instanceof PlayerSearchView);
    }

    @org.junit.Test
    public void dataDisplayFactory(){
        Object obj = PlayerDataDisplayViewFactory.create(playerDataDisplayViewModel, viewManagerModel, playerSearchViewModel, playerComparisonAddViewModel, playerComparisonDataAccessObject);
        assertTrue(obj instanceof PlayerDataDisplayView);
    }

    @org.junit.Test
    public void playerComparisonFactory(){
        Object obj = PlayerComparisonUseCaseFactory.create(playerComparisonViewModel, mainMenuViewModel, viewManagerModel, playerComparisonDataAccessObject, playerComparisonRemovePresenter, playerComparisonDataAccessObject, playerComparisonRemoveViewModel);
        assertTrue(obj instanceof PlayerComparisonView);
    }

    @org.junit.Test
    public void idSearchFactory(){
        Object obj = IDSearchUseCaseFactory.create(viewManagerModel, iDsearchViewModel, idSearchDataAccessObject, mainMenuViewModel);
        assertTrue(obj instanceof IDSearchView);
    }



}
