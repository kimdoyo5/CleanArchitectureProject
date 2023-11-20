package main.java.data_access;

import com.opencsv.CSVWriter;
import main.java.app.DatabaseCreationInterface;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class DatabaseDataAccessObject implements DatabaseCreationInterface {

    public DatabaseDataAccessObject(){}

    private void close(CSVWriter w){
        try{
            w.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public void create(){
        File database = new File("database.csv");
        try {
            if (!(database.createNewFile())) {
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<Integer> teams = getTeamIDs();
            FileWriter fileWriter = new FileWriter(database);
            CSVWriter writer = new CSVWriter(fileWriter);
            String[] header = {"ID", "Name"};
            writer.writeNext(header);
            close(writer);
            for (Integer team : teams) {
                FileWriter fileWriterTeam = new FileWriter(database, true);
                CSVWriter writerTeam = new CSVWriter(fileWriterTeam);
                Map<Integer, String> players = getPlayers(team);
                ArrayList<String[]> toWrite = new ArrayList<>();
                for (Integer key : players.keySet()) {
                    toWrite.add(new String[]{key.toString(), players.get(key)});
                }
                writerTeam.writeAll(toWrite);
                close(writerTeam);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<Integer> getTeamIDs(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        ArrayList<Request> requests = new ArrayList<>();
        ArrayList<Integer> team_ids = new ArrayList<>();
        for (int i = 2010; i < 2023; i++) {
            Request request = new Request.Builder()
                    .url(String.format("http://lookup-service-prod.mlb.com/json/named.team_all_season.bam?sport_code='mlb'&all_star_sw='N'&sort_order=name_asc&season='%s'", i))
                    .method("GET", null)
                    .build();
            requests.add(request);
        }
        try {

            for (Request req : requests){
                Response response = client.newCall(req).execute();
                JSONObject full_response = new JSONObject(response.body().string());
                JSONArray teams_rough = full_response.getJSONObject("team_all_season").getJSONObject("queryResults").getJSONArray("row");
                for (int i = 0; i < 30; i++){
                    JSONObject team  = teams_rough.getJSONObject(i);
                    Integer team_id = Integer.valueOf(team.getInt("mlb_org_id"));
                    if (team_id != 0){
                        if (!(team_ids.contains(team_id))){
                            team_ids.add(team_id);
                        }
                    }else{
                        throw new RuntimeException();
                    }
                }

            }
        }catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
        return team_ids;
    }
    private Map<Integer, String> getPlayers(int team) {
        Map<Integer, String> players = new HashMap<>();
        String thisYear = new SimpleDateFormat("yyyy").format(new java.util.Date());
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://lookup-service-prod.mlb.com/json/named.roster_team_alltime.bam?start_season='2017'&end_season='%s'&team_id='%s'", thisYear, team))
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject full_response = new JSONObject(response.body().string());
            JSONArray players_rough = full_response.getJSONObject("roster_team_alltime").getJSONObject("queryResults").getJSONArray("row");
            for (int i = 0; i < players_rough.length(); i++){
                JSONObject player  = players_rough.getJSONObject(i);
                Integer player_id = Integer.valueOf(player.getInt("player_id"));
                String player_name = player.getString("name_first_last");
                players.put(player_id, player_name);
            }
        }catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
        return players;
    }
}
