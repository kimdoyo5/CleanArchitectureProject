package main.java.data_access;

import main.java.use_case.id_search.IDSearchDataAccessInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDFileDataAccessObject implements IDSearchDataAccessInterface {
    private final File playerIDFile;
    private  final Map<String, Integer> playerID = new HashMap<>();

    public IDFileDataAccessObject(String filePath){
        playerIDFile =new File(filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(playerIDFile))){
            reader.readLine();
            String row;
            while((row = reader.readLine()) != null){
                String[] col = row.split(",");
                String playerName = String.valueOf(col[1]);
                int id = Integer.valueOf(col[0].substring(1, col[0].length()-1));
                playerID.put(playerName, id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return result;
    }
}
