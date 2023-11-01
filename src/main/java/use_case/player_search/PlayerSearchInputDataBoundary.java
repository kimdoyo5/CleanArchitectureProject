package use_case.player_search;

import java.io.IOException;

public interface PlayerSearchInputDataBoundary {
    void execute(PlayerSearchInputData playerSearchInputData) throws IOException;
}
