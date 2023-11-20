package main.java.interface_adapter.player_search;
import main.java.use_case.player_search.PlayerOutputData;
import main.java.use_case.player_search.PlayerSearchOutputBoundary;

public class PlayerSearchPresenter implements PlayerSearchOutputBoundary {

    @Override
    public void prepareSuccessView(PlayerOutputData player) {

    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void back() {

    }
}
