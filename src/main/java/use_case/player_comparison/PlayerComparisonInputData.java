package main.java.use_case.player_comparison;

public class PlayerComparisonInputData {

    boolean inputsin = false;
    int player1; // First player for comparison (left)
    int player2; // Second player for comparison (right)

    public PlayerComparisonInputData(int player_id1, int player_id2) {

        player1 = player_id1;
        player2 = player_id2;

        if (player1 != 0 & player2 != 0) {
            inputsin = true;
        }

    }




}
