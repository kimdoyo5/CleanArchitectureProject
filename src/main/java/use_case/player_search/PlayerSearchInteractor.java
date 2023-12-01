package main.java.use_case.player_search;

import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.use_case.id_search.IDSearchDataAccessInterface;


import java.io.IOException;

public class PlayerSearchInteractor implements PlayerSearchInputDataBoundary {

    final PlayerSearchOutputBoundary playerSearchOutputBoundary;
    final PlayerSearchDataAccessInterface playerSearchDataAccessInterface;
    final IDSearchDataAccessInterface idSearchDataInterface;
    String error = null;
    public PlayerSearchInteractor(PlayerSearchDataAccessInterface playerSearchDataAccessInterface,
                                  PlayerSearchOutputBoundary playerSearchOutputBoundary,
                                  IDSearchDataAccessInterface idSearchDataInterface){

        this.playerSearchOutputBoundary =  playerSearchOutputBoundary;
        this.playerSearchDataAccessInterface = playerSearchDataAccessInterface;
        this.idSearchDataInterface = idSearchDataInterface;
    }

    @Override
    public void execute(PlayerSearchInputData playerSearchInputData){

        if (playerSearchInputData.getPlayer_id() != 0){
            if (idSearchDataInterface.isPlayer(playerSearchInputData.getPlayer_id())) {
                try {
                    Player result = playerSearchDataAccessInterface.search(playerSearchInputData.getPlayer_id());
                    PlayerOutputData out = new PlayerOutputData(result);
                    playerSearchOutputBoundary.prepareSuccessView(out);
                }catch (RuntimeException | IOException e){
                    error = "Search Error";
                    playerSearchOutputBoundary.prepareFailView(error);
                }
            }else{
                error = "no id";
                playerSearchOutputBoundary.prepareFailView(error);
            }
        }
    }

    public String getError(){return error;}

    public void execute(){
        playerSearchOutputBoundary.back();
    }
}
