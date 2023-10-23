package use_case.PlayerSearch;

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

    }
}
