package main.java.entity;

import org.json.JSONObject;

import main.java.entity.Player;


import java.util.Map;

public interface PlayerFactory {
    Player create(JSONObject info, JSONObject stats);
}
