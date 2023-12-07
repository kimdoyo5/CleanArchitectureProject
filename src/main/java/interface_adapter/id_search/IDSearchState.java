package main.java.interface_adapter.id_search;

import java.util.HashMap;
import java.util.Map;

/**
 * State for the ID search view. Includes what the user typed in the search bar,
 * if there was an error when they clicked "search", and what the result of
 * their search was.
 */
public class IDSearchState {
    private String query = "";
    private String searchError = null;
    private String result = "";

    /**
     * Initializes the state. By default, searchError is null to correspond to
     * the fact that there has not been an error yet.
     */
    public IDSearchState() {}

    /**
     * Gives what the user typed in the search bar
     * @return what the user typed
     */
    public String getQuery() {
        return query;
    }

    /**
     * Gives the search error, will be null if there is not any
     * @return the attribute searchError
     */
    public String getSearchError() {
        return searchError;
    }

    /**
     * The result of the search which is all the matching players and IDs
     * @return the attribute result
     */
    public String getResult() {
        return result;
    }

    /**
     * Allows the user's search term to be updated
     * @param query the user's search term
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Allows for the addition of an error with the search
     * @param searchError a description of what caused the error
     */
    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    /**
     * Allows for the addition of the search results to be shown to the user
     * @param result a map of the matching players and IDs, will be iterated
     *               through to create a string to display and set as the
     *               result attribute
     */
    public void setResult(Map<String, Integer> result) {
        String toSet = "<html><body>";
        for (String key : result.keySet()){
            toSet += (key.substring(1, key.length()-1) + " has the ID: " + result.get(key) + "<br>");
        }
        toSet += "</body></html>";
        this.result = toSet;
    }
}
