package main.java.interface_adapter.player_search;

import java.security.PrivateKey;
import java.util.Map;

public class PlayerSearchState {
    private String search = "";
    private String search_error = null;
    private String result = "";

    PlayerSearchState(PlayerSearchState copy){
        this.result = copy.result;
        this.search_error = copy.search_error;
        this.search = copy.search;
    }

    PlayerSearchState(){}

    public String getSearch(){return search;}

    public void setSearch(String search){this.search = search;}

    public String getSearch_error(){return search_error;}

    public void setSearch_error(String search_error) {
        this.search_error = search_error;
    }

    public String getResult(){return result;}

    public void setResult(Map<String, String> data) {
        StringBuilder value = new StringBuilder();
        value.append("<html><body>");
        value.append("Name").append(": ").append(data.get("Name")).append("<br>");
        value.append("Player Id").append(": ").append(data.get("Player Id")).append("<br>");
        for (String key: data.keySet()){
            if (!key.equals("Name") && !key.equals("Player Id")) {
                value.append(key).append(": ").append(data.get(key)).append("<br>");
            }
        }
        value.append("</body></html>");
        result = value.toString();
    }

}
