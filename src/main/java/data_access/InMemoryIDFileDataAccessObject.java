package main.java.data_access;

import main.java.use_case.id_search.IDSearchDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryIDFileDataAccessObject implements IDSearchDataAccessInterface {
    private final Map<String, Integer> playerID;
    private boolean t = true;

    public InMemoryIDFileDataAccessObject(Map<String, Integer> players){
        this.playerID = players;
    }
    public InMemoryIDFileDataAccessObject(Map<String, Integer> players, boolean t){
        this.playerID = players;
        this.t = t;
    }
    public  boolean isPlayer(String name){
        for(String key: playerID.keySet()){
            if (key.toLowerCase().contains(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public  boolean isPlayer(int id){
        return playerID.containsValue(id);
    }

    public Map<String, Integer> getID(String name){
        Map<String, Integer> result = new HashMap<>();
        for(String key: playerID.keySet()){
            if (key.toLowerCase().contains(name.toLowerCase())){
                result.put(key, playerID.get(key));
            }
        }
        if (this.t){
            return result;
        }
        else{
            throw new RuntimeException("Exception thrown");
        }
    }
}
