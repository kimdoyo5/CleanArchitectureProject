package data_access;

import java.io.*;
import java.util.*;
import entity.Player;
import entity.PlayerFactory;

public class PlayerComparisonDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Player> players = new HashMap<>();

    private PlayerFactory playerFactory;

    public PlayerComparisonDataAccessObject(String csvName, PlayerFactory playerFactory) throws IOException {
        this.playerFactory = playerFactory;

        csvFile = new File(csvName);

        //add when player specifics are determined
        //headers.put();

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    //ADD PLAYER ATTRIBUTES
                    Player player = playerFactory.create(ADD_ATTRIBUTES);
                    players.put(playerName, player);
                }
            }
        }
    }

    @Override
    public void save(Player player) {
        players.put(player.getName(), user);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Player user : player.values()) {
                String line = String.format("%s,%s,%s",ADD_PLAYER_ATTRIBUTES);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player get(String username) {
        return players.get(username);
    }

}
