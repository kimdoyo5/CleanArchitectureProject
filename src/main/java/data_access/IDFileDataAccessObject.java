package main.java.data_access;

import main.java.use_case.id_search.IDSearchDataAccessInterface;

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
                int id = Integer.valueOf(col[10]);
                playerID.put(playerName.toLowerCase(), id);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean isPlayer(String name){
        return playerID.containsKey(name);
    }
    public  boolean isPlayer(int id){
        return playerID.containsValue(id);
    }
    public List<Integer> getID(String name){
        List<Integer> result = new ArrayList<>();
        for(String key: playerID.keySet()){
            if (key.contains(name.toLowerCase())){
                result.add(playerID.get(key));
            }
        }
        return result;
    }
}
