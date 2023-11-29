package main.java.interface_adapter.PlayerDataDisplay;

import main.java.interface_adapter.player_search.PlayerSearchState;

import java.util.Map;

public class PlayerDataDisplayViewState {

    private String result = "";

    PlayerDataDisplayViewState(PlayerDataDisplayViewState copy){
        this.result = copy.result;

    }

    PlayerDataDisplayViewState(){}

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
