package entity;

import org.json.JSONObject;

import java.util.Map;

public interface PlayerFactory {
    Player create(JSONObject info, JSONObject stats);
}
