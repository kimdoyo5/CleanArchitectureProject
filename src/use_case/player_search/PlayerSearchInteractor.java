package use_case.player_search;

import entity.Player;
import entity.PlayerFactory;

public class PlayerSearchInteractor implements PlayerSearchInputDataBoundary{

    PlayerSearchOutputBoundary playerSearchOutputBoundary;
    PlayerFactory playerDataFactory;
    PlayerSearchDataAccessInterface playerSearchDataAccessInterface;
    public PlayerSearchInteractor(PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                  PlayerSearchOutputBoundary playerSearchOutputBoundary,
                                  PlayerFactory playerDataFactory){

        this.playerDataFactory = playerDataFactory;
        this.playerSearchOutputBoundary =  playerSearchOutputBoundary;
        this.playerSearchDataAccessInterface = playerSearchDataAccessInterface;
    }

    public void execute(PlayerSearchInputData playerSearchInputData){
        if (playerSearchInputData.getPlayer_id() != 0){
            Player reuslt = playerSearchDataAccessInterface.search(playerSearchInputData.getPlayer_id());
        }else{
            Player result = playerSearchDataAccessInterface.search(playerSearchInputData.getName());
        }
    }
}
