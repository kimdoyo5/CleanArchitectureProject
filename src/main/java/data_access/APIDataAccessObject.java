package data_access;

import entity.Player;
import entity.PlayerFactory;
import use_case.player_search.PlayerSearchDataAccessInterface;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
public class APIDataAccessObject implements PlayerSearchDataAccessInterface {
    private IDFIleDataAccessObject idFile;
    private PlayerFactory playerFactory;
    public APIDataAccessObject(IDFIleDataAccessObject idfile, PlayerFactory playerFactory){
        this.idFile = idfile;
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
            JSONObject info = new JSONObject(response.body().string());
            JSONObject stats = new JSONObject(response2.body().string());
            return playerFactory.create(info, stats);
        }catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
    }

}
