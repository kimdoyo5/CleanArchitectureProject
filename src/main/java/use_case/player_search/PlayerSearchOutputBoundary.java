package main.java.use_case.player_search;

public interface PlayerSearchOutputBoundary {
    void prepareSuccessView(PlayerOutputData player);

    void prepareFailView(String error);

    void back();
}
