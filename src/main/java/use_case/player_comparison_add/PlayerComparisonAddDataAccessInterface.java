package main.java.use_case.player_comparison_add;

import main.java.entity.Player;

public interface PlayerComparisonAddDataAccessInterface {

    boolean add(Player player);

    int getSize();

}
