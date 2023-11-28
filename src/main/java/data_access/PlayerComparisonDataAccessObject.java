package main.java.data_access;

import java.io.*;
import java.util.*;
import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.use_case.player_compaison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison.PlayerComparisonDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;

public class PlayerComparisonDataAccessObject implements
        PlayerComparisonAddDataAccessInterface,
        PlayerComparisonRemoveDataAccessInterface,
        PlayerComparisonDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Player> leaders = new LinkedHashMap<>();
    private boolean leaders_updated = false;

    private PlayerFactory playerFactory;

    public PlayerComparisonDataAccessObject(String csvName, PlayerFactory playerFactory) throws IOException {
        this.playerFactory = playerFactory;

        csvFile = new File(csvName);

        headers.put("name", 0);
        headers.put("id", 1);
        headers.put("hr", 2);
        headers.put("tb", 3);
        headers.put("xbh", 4);
        headers.put("bb", 5);
        headers.put("h", 6);
        headers.put("cs", 7);
        headers.put("sb", 8);
        headers.put("ab", 9);
        headers.put("obp", 10);
        headers.put("slg", 11);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row;
                reader.readLine();
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    List<String> playerInfo = new ArrayList<>();
                    for(int i = 0; i < col.length; i++){
                        playerInfo.add(String.valueOf(col[i]));
                    }
                    Player player = playerFactory.create(playerInfo);
                    players.put(player.getName(), player);
                }
            }
        }
    }

    public void save(Player player) {
        players.put(player.getName(), player);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Player player : players.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        player.getName(),
                        player.getID(),
                        player.getStats().get("hr"),
                        player.getStats().get("tb"),
                        player.getStats().get("xbh"),
                        player.getStats().get("bb"),
                        player.getStats().get("h"),
                        player.getStats().get("cs"),
                        player.getStats().get("sb"),
                        player.getStats().get("ab"),
                        player.getStats().get("obp"),
                        player.getStats().get("slg")
                );
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

    public boolean existsByName(String identifier) {                     //May not be needed
        return players.containsKey(identifier);
    }

    public boolean add(Player player) {
        if (players.size() >= 4) {
            return false;
        } else {
            players.put(player.getName(), player);
            this.save();
            leaders_updated = false;
            return true;
        }
    }

    public int playersAdded() {
        return players.size();
    }

    public Map getLeaders() {
        if (leaders_updated) {
            return leaders;
        } else {
            // Find the best player for each stat
            for (each of 4 players) {
                if (each.hr > leaders.get) {
                    leaders.put("hr", each.getName());
                }
                if (each.tb > leaders.get) {
                    leaders.put("tb", each.getName());
                }
                if (each.xbh > leaders.get) {
                    leaders.put("xbh", each.getName());
                }
                if (each.bb > leaders.get) {
                    leaders.put("bb", each.getName());
                }
                if (each.h > leaders.get) {
                    leaders.put("h", each.getName());
                }
                if (each.cs < leaders.get) {
                    leaders.put("cs", each.getName());
                }
                if (each.sb > leaders.get) {
                    leaders.put("sb", each.getName());
                }
                if (each.ab > leaders.get) {
                    leaders.put("ab", each.getName());
                }
                if (each.obp > leaders.get) {
                    leaders.put("obp", each.getName());
                }
                if (each.slg > leaders.get) {
                    leaders.put("slg", each.getName());
                }
            }
            leaders_updated = true;
            return leaders;
        }
    }

    public Player remove(int playerId){
        Set<String> keys = players.keySet();
        Player player = null;
        for(String key: keys){
            if (players.get(key).getID() == playerId){
                player = players.get(key);
                players.remove(key);
                this.save();
            }
        }
        return player;
    }
}
