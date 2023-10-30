package data_access;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IDFIleDataAccessObject {
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

    public int getID(String name){
        return playerID.get(name);
    }
}
