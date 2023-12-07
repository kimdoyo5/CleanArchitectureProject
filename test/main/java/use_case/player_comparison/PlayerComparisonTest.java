package main.java.use_case.player_comparison;

import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.entity.Player;
import main.java.interface_adapter.player_comparison.PlayerComparisonPresenter;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.navigation.MainMenuViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerComparisonTest {
    private PlayerComparisonDataAccessInterface dataAccess;
    private FakePlayerComparisonOutputBoundary outputBoundary;
    private PlayerComparisonInteractor interactor;
    private PlayerComparisonPresenter presenter;

    @BeforeEach
    public void setUp() throws IOException {
        dataAccess = new PlayerComparisonDataAccessObject("./playercomparison.csv", new CommonPlayerFactory());
        outputBoundary = new FakePlayerComparisonOutputBoundary();
        PlayerComparisonViewModel viewModel = new PlayerComparisonViewModel();
        MainMenuViewModel navigationViewModel = new MainMenuViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        presenter = new PlayerComparisonPresenter(viewModel, navigationViewModel, viewManagerModel);
        interactor = new PlayerComparisonInteractor(dataAccess, outputBoundary);
    }

    @Test
    public void testExecuteWithTwoOrMorePlayers() {
        addTestPlayers(2);
        PlayerComparisonOutputBoundary testPresenter = new PlayerComparisonOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonOutputData dataArray) {
                assert(true);
            }

            @Override
            public void prepareFailView(String error) {
                assert(false);
            }

            @Override
            public void back() {
                assert (false);
            }
        };
        PlayerComparisonInteractor interactor1 = new PlayerComparisonInteractor(dataAccess, testPresenter);
        interactor1.execute();

    }

    @Test
    public void testExecuteDataArrayFormation() {
        addTestPlayers(2);
        PlayerComparisonOutputBoundary testPresenter = new PlayerComparisonOutputBoundary(){
            @Override
            public void prepareSuccessView(PlayerComparisonOutputData dataArray) {
                assertNotNull(dataArray.getDataArray());
                assertTrue(dataArray.getDataArray().length > 0);
            }

            @Override
            public void prepareFailView(String error) {
                assert(false);
            }

            @Override
            public void back() {
                assert (false);
            }
        };
        PlayerComparisonInteractor interactor1 = new PlayerComparisonInteractor(dataAccess, testPresenter);
        interactor1.execute();
    }

    @Test
    public void testBackFunctionality() {
        interactor.back();
        assertTrue(outputBoundary.isBackCalled());
    }

    @Test
    public void testExecuteWithMoreThanTwoPlayers() {
        PlayerComparisonRemoveDataAccessInterface data = (PlayerComparisonRemoveDataAccessInterface)dataAccess;
        data.removedPlayers();
        addTestPlayers(3); // Adding more than two players

        interactor.execute();
        assertTrue(outputBoundary.isSuccessViewPrepared());
        assertNotNull(outputBoundary.getDataArray());
        assertEquals(3 + 2, outputBoundary.getDataArray()[0].length); // Check if columns are correctly formed
    }

    @Test
    public void testPlayerComparisonOutputData() {
        addTestPlayers(2);
        interactor.execute();

        PlayerComparisonOutputData outputData = new PlayerComparisonOutputData(outputBoundary.getDataArray());
        assertNotNull(outputData.getDataArray());
        assertEquals(outputBoundary.getDataArray(), outputData.getDataArray());
    }

    private void addInvalidTestPlayer() {
        List<String> invalidPlayerInfo = List.of("InvalidPlayer", "NaN");
        Player invalidPlayer = new CommonPlayerFactory().create(invalidPlayerInfo);
        dataAccess.add(invalidPlayer);
    }

    private void addTestPlayers(int count) {
        for (int i = 0; i < count; i++) {
            List<String> playerInfo = new ArrayList<>();
            playerInfo.add("Player " + i);
            playerInfo.add(String.valueOf(i));
            for (int j = 2; j < 18; j++) {
                playerInfo.add(String.valueOf(j - 1));
            }
            Player player = new CommonPlayerFactory().create(playerInfo);
            dataAccess.add(player);
        }
    }

    private class FakePlayerComparisonOutputBoundary implements PlayerComparisonOutputBoundary {
        private boolean successViewPrepared = false;
        private boolean failViewPrepared = false;
        private boolean backCalled = false;
        private String errorMessage;
        private String[][] dataArray;

        @Override
        public void prepareSuccessView(PlayerComparisonOutputData dataArray) {
            this.successViewPrepared = true;
            this.dataArray = dataArray.getDataArray();
        }

        @Override
        public void prepareFailView(String error) {
            this.failViewPrepared = true;
            this.errorMessage = error;
        }

        @Override
        public void back() {
            this.backCalled = true;
        }

        public boolean isSuccessViewPrepared() {
            return successViewPrepared;
        }

        public boolean isFailViewPrepared() {
            return failViewPrepared;
        }

        public boolean isBackCalled() {
            return backCalled;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String[][] getDataArray() {
            return dataArray;
        }
    }
}



