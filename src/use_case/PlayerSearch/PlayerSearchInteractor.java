package use_case.PlayerSearch;

import entity.PlayerDataFactory;

public class PlayerSearchInteractor implements PlayerSearchInputDataBoundary{

    PlayerSearchOutputBoundary playerSearchOutputBoundary;
    PlayerDataFactory playerDataFactory;
    PlayerSearchDataAccessInterface playerSearchDataAccessInterface;
    public PlayerSearchInteractor(PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                  PlayerSearchOutputBoundary playerSearchOutputBoundary,
                                  PlayerDataFactory playerDataFactory){

        this.playerDataFactory = playerDataFactory;
        this.playerSearchOutputBoundary =  playerSearchOutputBoundary;
        this.playerSearchDataAccessInterface = playerSearchDataAccessInterface;
    }

    public void execute(PlayerSearchInputData playerSearchInputData){

    }
}
