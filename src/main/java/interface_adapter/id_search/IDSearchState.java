package main.java.interface_adapter.id_search;

import java.util.HashMap;
import java.util.Map;

public class IDSearchState {
    private String query = "";
    private String searchError = null;
    private String result = "";

    public IDSearchState() {}

    public String getQuery() {
        return query;
    }

    public String getSearchError() {
        return searchError;
    }

    public String getResult() {
        return result;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setResult(Map<String, Integer> result) {
        String toSet = "<html><body>";
        for (String key : result.keySet()){
            toSet += (key.substring(1, key.length()-1) + " has the ID: " + result.get(key) + "<br>");
        }
        toSet += "</body></html>";
        this.result = toSet;
    }
}
