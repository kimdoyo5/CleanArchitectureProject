package main.java.data_access;


import main.java.entity.Player;
import main.java.entity.PlayerFactory;

import main.java.use_case.player_search.PlayerSearchDataAccessInterface;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import main.java.data_access.IDFileDataAccessObject;

import java.io.IOException;
public class APIDataAccessObject implements PlayerSearchDataAccessInterface {

    private PlayerFactory playerFactory;
    public APIDataAccessObject(PlayerFactory playerFactory){

        this.playerFactory = playerFactory;
    }


    @Override
    public Player search(int player_id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(String.format("http://lookup-service-prod.mlb.com/json/named.player_info.bam?sport_code='mlb'&player_id='%s'", player_id))
                .method("GET", null)
                .build();
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType2 = MediaType.parse("text/plain");
        Request request2 = new Request.Builder()
                .url(String.format("http://lookup-service-prod.mlb.com/json/named.sport_career_hitting.bam?league_list_id='mlb'&game_type='R'&player_id='%s'", player_id))
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Response response2 = client.newCall(request2).execute();
            JSONObject info_rough = new JSONObject(response.body().string());
            JSONObject stats_rough = new JSONObject(response2.body().string());
            JSONObject info = info_rough.getJSONObject("player_info").getJSONObject("queryResults");
            JSONObject stats = stats_rough.getJSONObject("sport_career_hitting").getJSONObject("queryResults");
            if (info.getInt("totalSize") > 0 && stats.getInt("totalSize") > 0){
                return playerFactory.create(info.getJSONObject("row"), stats.getJSONObject("row"));
            }else{
                System.out.println("no player");
                throw new RuntimeException();
            }
        }catch (IOException | JSONException e){
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

}
