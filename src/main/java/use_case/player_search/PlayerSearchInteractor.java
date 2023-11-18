package main.java.use_case.player_search;

import entity.Player;
import entity.PlayerFactory;
import use_case.PlayerIDSearch.IDSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchInputDataBoundary;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;
import main.java.use_case.player_search.PlayerSearchDataAccessInterface;
import main.java.use_case.player_search.PlayerSearchInputData;
import main.java.use_case.player_search.PlayerOutputData;


import java.io.IOException;

public class PlayerSearchInteractor implements PlayerSearchInputDataBoundary{

    PlayerSearchOutputBoundary playerSearchOutputBoundary;
    PlayerFactory playerDataFactory;
    PlayerSearchDataAccessInterface playerSearchDataAccessInterface;
    IDSearchDataAccessInterface idSearchDataInterface;
    public PlayerSearchInteractor(PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                  PlayerSearchOutputBoundary playerSearchOutputBoundary,
                                  PlayerFactory playerDataFactory, IDSearchDataAccessInterface idSearchDataInterface){

        this.playerDataFactory = playerDataFactory;
        this.playerSearchOutputBoundary =  playerSearchOutputBoundary;
        this.playerSearchDataAccessInterface = playerSearchDataAccessInterface;
        this.idSearchDataInterface = idSearchDataInterface;
    }

    public void execute(PlayerSearchInputData playerSearchInputData) throws IOException {

        if (playerSearchInputData.getPlayer_id() != 0){
            if (idSearchDataInterface.isPlayer(playerSearchInputData.getPlayer_id())) {
                try {
                    Player result = playerSearchDataAccessInterface.search(playerSearchInputData.getPlayer_id());
                    PlayerOutputData out = new PlayerOutputData(result);
                    playerSearchOutputBoundary.prepareSuccessView(out);
                }catch (RuntimeException e){
                    playerSearchOutputBoundary.prepareFailView("Search Error");
                }
            }else{
                playerSearchOutputBoundary.prepareFailView("no id");
            }
        }
    }
}
