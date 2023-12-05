package main.java.use_case.player_comparison_remove;

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

public class PlayerComparisonRemoveTest {

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

        PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface = new TestPlayerAddRemoveDataAccessObject();
        playerComparisonRemoveDataAccessInterface.add(player);
        PlayerComparisonRemoveOutputBoundary playerComparisonRemovePresenter = new PlayerComparisonRemoveOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonRemoveOutputData playerComparisonRemoveOutputData) {
                assertEquals("Billy Bob", playerComparisonRemoveOutputData.getRemovedPlayers().get(0));
                assertEquals(0, playerComparisonRemoveDataAccessInterface.getSize());
            }

            @Override
            public void prepareFailView(String error) {
                fail("successTest failed");
            }
        };

        PlayerComparisonRemoveInputBoundary playerComparisonRemoveInteractor = new PlayerComparisonRemoveInteractor(playerComparisonRemoveDataAccessInterface, playerComparisonRemovePresenter);
        playerComparisonRemoveInteractor.execute();
    }

    @Test
    void failTest(){

        PlayerComparisonRemoveDataAccessInterface playerComparisonRemoveDataAccessInterface = new TestPlayerAddRemoveDataAccessObject();
        PlayerComparisonRemoveOutputBoundary playerComparisonRemovePresenter = new PlayerComparisonRemoveOutputBoundary() {
            @Override
            public void prepareSuccessView(PlayerComparisonRemoveOutputData playerComparisonRemoveOutputData) {
                fail("failTest failed");

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "No players in the comparison");
                assertEquals(0, playerComparisonRemoveDataAccessInterface.getSize());
            }
        };

        PlayerComparisonRemoveInputBoundary playerComparisonRemoveInteractor = new PlayerComparisonRemoveInteractor(playerComparisonRemoveDataAccessInterface, playerComparisonRemovePresenter);
        playerComparisonRemoveInteractor.execute();
    }

}
