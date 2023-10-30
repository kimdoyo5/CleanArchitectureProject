package data_access;

import entity.Player;
import entity.PlayerFactory;
import use_case.player_search.PlayerSearchDataAccessInterface;

public class APIDataAccessObject implements PlayerSearchDataAccessInterface {
    private IDFIleDataAccessObject idFile;
    private PlayerFactory playerFactory;
    public void APIDataAccessObject(IDFIleDataAccessObject idfile, PlayerFactory playerFactory){
        this.idFile = idfile;
        this.playerFactory = playerFactory;
    }


    @Override
    public Player search(int player_id) {
        return null;
    }

    @Override
    public Player search(String player_name) {
        int id = idFile.getID(player_name);
        return search(id);
    }
}
