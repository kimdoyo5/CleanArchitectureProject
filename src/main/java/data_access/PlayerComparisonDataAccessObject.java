package main.java.data_access;

import java.io.*;
import java.util.*;
import main.java.entity.Player;
import main.java.entity.PlayerFactory;
import main.java.use_case.player_comparison_remove.PlayerComparisonRemoveDataAccessInterface;
import main.java.use_case.player_comparison.PlayerComparisonDataAccessInterface;
import main.java.use_case.player_comparison_add.PlayerComparisonAddDataAccessInterface;

/**
 * Obtains requested data from playercomparison.csv
 */
public class PlayerComparisonDataAccessObject implements
        PlayerComparisonAddDataAccessInterface,
        PlayerComparisonRemoveDataAccessInterface,
        PlayerComparisonDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, String> leaders = new LinkedHashMap<>();
    private boolean leaders_updated = false;

    private PlayerFactory playerFactory;

    public List<String> getAllPlayerNames() {
        return new ArrayList<>(players.keySet());
    }

    /**
     * Constructor of the class
     * @param csvName the file path this data access object should be connected to
     * @param playerFactory The factory used to create player objects
     * @throws IOException may occur if an issue reading the file occurs
     */
    public PlayerComparisonDataAccessObject(String csvName, PlayerFactory playerFactory) throws IOException {
        this.playerFactory = playerFactory;

        csvFile = new File(csvName);

        String[] headerNames = {
                "name", "id", "hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab",
                "obp", "slg", "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS" };

        for (int i = 0; i < headerNames.length; i++) {
            headers.put(headerNames[i], i);
        }

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

    /**
     * Writes the current player objects in players into the connected csv file
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            // Define the keys for the stats
            String[] statKeys = {
                    "hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg",
                    "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS"};

            for (Player player : players.values()) {
                List<String> lineParts = new ArrayList<>();

                // Add non-stat attributes
                lineParts.add(player.getName());
                lineParts.add(String.valueOf(player.getID()));

                // Add stats
                for (String key : statKeys) {
                    if (player.getStats().containsKey(key)) {
                        lineParts.add(String.valueOf(player.getStats().get(key))); // Basic stats
                    } else {
                        lineParts.add(String.valueOf(player.calculateState(key))); // Advanced stats
                    }
                }

                String line = String.join(",", lineParts);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the first player with the same name
     * @param name the name of the desired player to be retrieved
     * @return player object with same name as name
     */
    public Player get(String name) {
        return players.get(name);
    }

    /**
     * Verifies if a player object can be added to the players map, and adds the player accordingly
     * @param player the player object being attempted to add to the players map
     * @return boolean based on if player has been added to players
     */
    public boolean add(Player player) {
        int playerId = player.getID();
        boolean alreadyIn = false;
        for (String key: players.keySet()){
            if (players.get(key).getID() == playerId){
                alreadyIn = true;
            }
        }

        if (players.size() >= 4 || alreadyIn) {
            return false;
        } else {
            players.put(player.getName(), player);
            this.save();
            leaders_updated = false;
            return true;
        }
    }

    /**
     * Gets the size of the players map
     * @return an int describing the number of player maps in players
     */
    public int getSize(){
        return players.size();
    }

    public Map<String, Double> getSimplified(String name) {
        Player player = get(name);
        Map<String,Double> simple = new HashMap<>();

        String[] statKeys = {
                "hr", "tb", "xbh", "bb", "h", "cs", "sb", "ab", "obp", "slg",
                "HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS" };
        for (String stat : statKeys) {
            simple.put(stat, getStatValue(player, stat));
        }
        return simple;
    }

    public Map<String, String> getLeaders() {
        if (!leaders_updated) {
            for (String stat : headers.keySet()) {
                if (!stat.equals("name") && !stat.equals("id")) {
                    leaders.put(stat, null);
                }
            }
            for (Player player : players.values()) {
                updateLeadersWithPlayer(player);
            }
            leaders_updated = true;
        }
        return leaders;
    }

    private void updateLeadersWithPlayer(Player player) {
        for (String stat : headers.keySet()) {
            if (!stat.equals("name") && !stat.equals("id")) {
                String currentLeaderName = leaders.get(stat);
                Player currentLeader = currentLeaderName != null ? players.get(currentLeaderName) : null;
                boolean shouldUpdate = false;

                double currentPlayerStatValue = getStatValue(player, stat);
                double currentLeaderStatValue = currentLeader != null ? getStatValue(currentLeader, stat) : (stat.equals("cs") ? Double.MAX_VALUE : Double.MIN_VALUE);

                if (stat.equals("cs") | stat.equals("CS_rate")) {
                    shouldUpdate = currentPlayerStatValue < currentLeaderStatValue;
                } else {
                    shouldUpdate = currentPlayerStatValue > currentLeaderStatValue;
                }

                if (shouldUpdate) {
                    leaders.put(stat, player.getName());
                }
            }
        }
    }

    private double getStatValue(Player player, String stat) {
        if (Arrays.asList("HR_rate", "CS_rate", "HBB_rate", "HH_rate", "OPS", "wOPS").contains(stat)) {
            try {
                return Double.parseDouble(player.calculateState(stat));
            } catch (NumberFormatException e) {
                return 0.0; // Arbitrary default value
            }
        } else {
            try {
                return Double.parseDouble(player.getStats().getOrDefault(stat, "0.0"));
            } catch (NumberFormatException e) {
                return 0.0; // Arbitrary default value
            }
        }
    }

    /**
     * Removes all elements from players lis
     * @return string array of all names of the players that have been removed
     */
    public List<String> removedPlayers(){
        Set<String> keys = players.keySet();
        List<String> playerNames = new ArrayList<>();
        for(String key: keys){
            playerNames.add(key);
        }
        players.clear();
        this.delete();
        return playerNames;

    }

    private void delete(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
