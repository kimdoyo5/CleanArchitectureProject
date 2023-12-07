package main.java.use_case.id_search;

import java.util.Map;

/**
 * The data passed by the interactor to the presenter. Contains a map with
 * all the search result player names as keys, mapped to those players' IDs.
 */
public class IDSearchOutputData {
    private Map<String, Integer> players;

    /**
     * Initializes the class
     * @param players map of search results
     */
    public IDSearchOutputData(Map<String, Integer> players){
        this.players = players;
    }

    /**
     * Gives the search results
     * @return the map of search results
     */
    public Map<String, Integer> getPlayers(){
        return players;
    }
}
