package main.java.interface_adapter.player_search;

import java.security.PrivateKey;
import java.util.Map;

/**
 * The state of the player search view
 * What is in the search bar and what is the result of search
 */
public class PlayerSearchState {
    private String search = "";
    private String search_error = null;


    /**
     * Constructs a copy of the class using an existing version of the class
     * @param copy An existing version of the class
     */
    public PlayerSearchState(PlayerSearchState copy){
        this.search_error = copy.search_error;
        this.search = copy.search;
    }

    /**
     * Constructs the class
     */
    public PlayerSearchState(){}

    /**
     * Retrieves what is in the search bar
     * @return The thing that is typed in the search bar so far
     */
    public String getSearch(){return search;}

    /**
     * Sets what is being searched
     * @param search the string based on what the user has inputted so far
     */
    public void setSearch(String search){this.search = search;}

    /**
     * Gets the error of the search
     * @return the error that might have happened in the search
     */
    public String getSearch_error(){return search_error;}

    /**
     * Sets the error
     * @param search_error the error that occurred
     */
    public void setSearch_error(String search_error) {
        this.search_error = search_error;
    }

}
