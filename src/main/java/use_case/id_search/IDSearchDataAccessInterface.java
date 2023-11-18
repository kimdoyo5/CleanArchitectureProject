package main.java.use_case.id_search;

import java.util.List;

public interface IDSearchDataAccessInterface {
    boolean isPlayer(int id);
    List<Integer> getID(String name);
}
