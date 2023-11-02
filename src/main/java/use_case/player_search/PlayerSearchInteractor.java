package use_case.player_search;

import entity.Player;
import entity.PlayerFactory;
import use_case.PlayerIDSearch.IDSearchDataAccessInterface;

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
                Player reuslt = playerSearchDataAccessInterface.search(playerSearchInputData.getPlayer_id());
            }else{
                playerSearchOutputBoundary.prepareFailView("no id");
            }
        }
    }
}
