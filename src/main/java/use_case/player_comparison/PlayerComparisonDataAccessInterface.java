package main.java.use_case.player_comparison;

import main.java.entity.Player;

import java.util.List;
import java.util.Map;

public interface PlayerComparisonDataAccessInterface {

    int playersAdded();
    List<String> getAllPlayerNames();
    Map<String, Double> getSimplified(String name);
    Map<String, String> getLeaders();

}
