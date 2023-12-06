package main.java.use_case.player_comparison;

import main.java.data_access.PlayerComparisonDataAccessObject;
import main.java.data_access.TestPlayerAddRemoveDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.entity.Player;
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

    @BeforeEach
    public void setUp() throws IOException {
        dataAccess = new PlayerComparisonDataAccessObject(csvName, playerFactory);
        outputBoundary = new FakePlayerComparisonOutputBoundary();
        interactor = new PlayerComparisonInteractor(dataAccess, outputBoundary, new CommonPlayerFactory());
    }

    @Test
    public void testExecuteWithLessThanTwoPlayers() {
        interactor.execute();
        assertTrue(outputBoundary.isFailViewPrepared());
        assertEquals("You need to select at least 2 players.", outputBoundary.getErrorMessage());
    }

    @Test
    public void testExecuteWithTwoOrMorePlayers() {
        addTestPlayers(2);

        interactor.execute();
        assertTrue(outputBoundary.isSuccessViewPrepared());
    }

    @Test
    public void testExecuteDataArrayFormation() {
        addTestPlayers(2);

        interactor.execute();
        assertNotNull(outputBoundary.getDataArray());
        assertTrue(outputBoundary.getDataArray().length > 0);
    }

    @Test
    public void testBackFunctionality() {
        interactor.back();
        assertTrue(outputBoundary.isBackCalled());
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


