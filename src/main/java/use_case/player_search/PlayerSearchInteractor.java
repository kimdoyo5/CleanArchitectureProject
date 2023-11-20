package main.java.use_case.player_search;
import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.use_case.id_search.IDSearchDataAccessInterface;

import java.io.IOException;

public class PlayerSearchInteractor implements PlayerSearchInputDataBoundary {

    PlayerSearchOutputBoundary playerSearchOutputBoundary;
    PlayerSearchDataAccessInterface playerSearchDataAccessInterface;
    IDSearchDataAccessInterface idSearchDataInterface;
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
                    Player reuslt = playerSearchDataAccessInterface.search(playerSearchInputData.getPlayer_id());
                    PlayerOutputData out = new PlayerOutputData(reuslt);
                    playerSearchOutputBoundary.prepareSuccessView(out);
                }catch (RuntimeException | IOException e){
                    playerSearchOutputBoundary.prepareFailView("Search Error");
                }
            }else{
                playerSearchOutputBoundary.prepareFailView("no id");
            }
        }
    }

    public void execute(){
        playerSearchOutputBoundary.back();
    }
}
