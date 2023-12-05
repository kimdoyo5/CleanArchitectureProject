package main.java.use_case.id_search;

import java.util.Map;

public class IDSearchOutputData {
    private Map<String, Integer> players;
    public IDSearchOutputData(Map<String, Integer> players){
        this.players = players;
    }

    public Map<String, Integer> getPlayers(){
        return players;
    }
}
