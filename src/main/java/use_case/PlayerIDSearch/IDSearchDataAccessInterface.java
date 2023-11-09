package use_case.PlayerIDSearch;

import java.util.List;

public interface IDSearchDataAccessInterface {
    boolean isPlayer(int id);
    List<Integer> getID(String name);
}
