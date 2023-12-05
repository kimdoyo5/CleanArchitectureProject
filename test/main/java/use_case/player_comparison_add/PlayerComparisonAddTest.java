package main.java.use_case.player_comparison_add;

import main.java.data_access.TestPlayerAddRemoveDataAccessObject;
import main.java.entity.CommonPlayerFactory;
import main.java.use_case.player_comparison_add.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.entity.Player;
import main.java.entity.CommonPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerComparisonAddTest {

    @Test
    void successTest(){
        List<String> playerInfo = new ArrayList<>();
        playerInfo.add("Billy Bob");
        playerInfo.add("111");
        for (int i = 2; i < 18; i++){
            playerInfo.add(Integer.toString(i-1));
        }
        CommonPlayerFactory commonPlayerFactory = new CommonPlayerFactory();
        Player player = commonPlayerFactory.create(playerInfo);
        PlayerComparisonAddInputData playerComparisonAddInputData = new PlayerComparisonAddInputData(player);
        PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface = new TestPlayerAddRemoveDataAccessObject();
        PlayerComparisonAddOutputBoundary playerComparisonAddPresenter = new PlayerComparisonAddOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonAddOutputData playerComparisonAddOutputData) {
                assertEquals("Billy Bob", playerComparisonAddOutputData.getPlayer().getName());
                assertEquals(111, playerComparisonAddOutputData.getPlayer().getID());
                assertEquals("11", playerComparisonAddOutputData.getPlayer().getStats().get("HR_rate"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("successTest failed");
            }
        };

        PlayerComparisonAddInputBoundary playerComparisonAddInteractor = new PlayerComparisonAddInteractor(playerComparisonAddDataAccessInterface, playerComparisonAddPresenter);
        playerComparisonAddInteractor.execute(playerComparisonAddInputData);
    }

    @Test
    void failComparisonFull(){
        List<String> playerInfo = new ArrayList<>();
        playerInfo.add("Billy Bob");
        playerInfo.add("111");
        for (int i = 2; i < 18; i++){
            playerInfo.add(Integer.toString(i-1));
        }
        CommonPlayerFactory commonPlayerFactory = new CommonPlayerFactory();
        Player player = commonPlayerFactory.create(playerInfo);
        PlayerComparisonAddInputData playerComparisonAddInputData = new PlayerComparisonAddInputData(player);
        PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface = new TestPlayerAddRemoveDataAccessObject();
        for(int i = 0; i < 4; i++){
            List<String> playerInfoTests = new ArrayList<>();
            playerInfoTests.add(Integer.toString(i));
            playerInfoTests.add(Integer.toString(i));
            for (int j = 0; j < 16; j++){
                playerInfoTests.add("-1");
            }
            Player tests = commonPlayerFactory.create(playerInfoTests);
            playerComparisonAddDataAccessInterface.add(tests);
        }

        PlayerComparisonAddOutputBoundary playerComparisonAddPresenter = new PlayerComparisonAddOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonAddOutputData playerComparisonAddOutputData) {
                fail("test failComparisonFull failed");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Max amount(4) of players already added to the player comparison", error);
            }
        };

        PlayerComparisonAddInputBoundary playerComparisonAddInteractor = new PlayerComparisonAddInteractor(playerComparisonAddDataAccessInterface, playerComparisonAddPresenter);
        playerComparisonAddInteractor.execute(playerComparisonAddInputData);

    }

    @Test
    void failComparisonSamePlayer(){
        List<String> playerInfo = new ArrayList<>();
        playerInfo.add("Billy Bob");
        playerInfo.add("111");
        for (int i = 2; i < 18; i++){
            playerInfo.add(Integer.toString(i-1));
        }
        CommonPlayerFactory commonPlayerFactory = new CommonPlayerFactory();
        Player player = commonPlayerFactory.create(playerInfo);
        PlayerComparisonAddInputData playerComparisonAddInputData = new PlayerComparisonAddInputData(player);
        PlayerComparisonAddDataAccessInterface playerComparisonAddDataAccessInterface = new TestPlayerAddRemoveDataAccessObject();
        playerComparisonAddDataAccessInterface.add(player);
        PlayerComparisonAddOutputBoundary playerComparisonAddPresenter = new PlayerComparisonAddOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonAddOutputData playerComparisonAddOutputData) {
                fail("test failComparisonFull failed");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Player already added to player comparison", error);
            }
        };

        PlayerComparisonAddInputBoundary playerComparisonAddInteractor = new PlayerComparisonAddInteractor(playerComparisonAddDataAccessInterface, playerComparisonAddPresenter);
        playerComparisonAddInteractor.execute(playerComparisonAddInputData);

    }


}
