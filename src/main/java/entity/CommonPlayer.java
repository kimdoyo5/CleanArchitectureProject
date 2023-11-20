package main.java.entity;

import java.util.Map;

public class CommonPlayer implements Player{
    String name;
    int id;
    Map<String, String> stats;
    CommonPlayer(String name, int id, Map<String, String> stats){
        this.name = name;
        this.id = id;
        this.stats = stats;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Map<String, String> getStats() {
        return stats;
    }
}
