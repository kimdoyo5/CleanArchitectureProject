package data_access;

import use_case.PlayerIDSearch.IDSearchDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IDFIleDataAccessObject implements IDSearchDataAccessInterface {
    private final File playerIDFile;

    private  final Map<String, Integer> playerID = new HashMap<>();

    public IDFIleDataAccessObject(String filePath){
        playerIDFile =new File(filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(playerIDFile))){
            reader.readLine();
            String row;
            while((row = reader.readLine()) != null){
                String[] col = row.split(",");
                String playerName = String.valueOf(col[1]);
                int id = Integer.valueOf(col[10]);
                playerName.toLowerCase();
                playerID.put(playerName, id);
            }

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
    public int getID(String name){
        return playerID.get(name);
    }
}
