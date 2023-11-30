package main.java.entity;


import java.util.Map;


public interface Player {
    String getName();
    int getID();
    Map<String, String> getStats();
    String calculateState(String target);
}
