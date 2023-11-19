package main.java.use_case.id_search;

import java.util.List;
import java.util.Map;

public interface IDSearchOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(Map<String, Integer> results);
}
