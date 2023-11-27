package main.java.entity;

import java.util.HashMap;
import java.util.Map;

public class CommonPlayer implements Player{
    String name;
    int id;
    Map<String, String> stats;

    private Map<String, Double> num_stats = new HashMap<>();
    CommonPlayer(String name, int id, Map<String, String> stats){
        this.name = name;
        this.id = id;
        this.stats = stats;
        for (String key: stats.keySet()){
            num_stats.put(key, Double.valueOf(stats.get(key)));
        }
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

    @Override
    public String calculateState(String target){
        if (target.equals("HR_rate")) {
            double stat = (double) num_stats.get("hr") /num_stats.get("h");
            return String.valueOf(stat);
        } else if (target.equals("CS_rate")) {
            double stat = num_stats.get("cs") /(num_stats.get("cs")+num_stats.get("sb"));
            return String.valueOf(stat);
        } else if(target.equals("HBB_rate")){
            double stat = (num_stats.get("h")+num_stats.get("bb"))/(num_stats.get("ab")+num_stats.get("bb"));
            return String.valueOf(stat);
        } else if (target.equals("HH_rate")){
            double stat = (double) (num_stats.get("xbh"))/num_stats.get("h");
            return String.valueOf(stat);
        } else if (target.equals("OPS")){
            double stat = (double) num_stats.get("obp") + num_stats.get("slg");
            return String.valueOf(stat);
        } else{
            double stat = (Double.valueOf(calculateState("OPS"))+(num_stats.get("hr")*10.792))/num_stats.get("h");
            return String.valueOf(stat);
        }
    }
}
