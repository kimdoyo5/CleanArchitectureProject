package main.java.use_case.id_search;

import java.util.List;
import java.util.Map;

public interface IDSearchDataAccessInterface {
    boolean isPlayer(int id);
    boolean isPlayer(String name);
    Map<String, Integer> getID(String name);
}
