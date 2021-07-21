package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.Config;
import com.keldoters.advisor.DataManager;
import com.keldoters.advisor.resource.Playlist;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class GetFeatured implements API {

    public void get(DataManager data, Auth client) {
        try {
            String response = getResponse(Config.API_PATH+ Config.FEATURED, client);
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonObject playlistObject = json.getAsJsonObject("playlists");
            for (JsonElement item : playlistObject.getAsJsonArray("items")) {
                JsonObject playlist = item.getAsJsonObject();
                JsonObject externalURL = playlist.getAsJsonObject("external_urls");
                String link = externalURL.get("spotify").getAsString();
                String playlistName = playlist.get("name").getAsString();
                Playlist playlistObj = new Playlist(playlistName,link);
                data.addFeatured(playlistObj);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
