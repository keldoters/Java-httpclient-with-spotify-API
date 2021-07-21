package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.Config;
import com.keldoters.advisor.DataManager;
import com.keldoters.advisor.resource.Playlist;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class GetPlaylists implements API {

    public void get(DataManager data, Auth client) {
        if (data.getSelectedCategory() == null) {
            return;
        }
        String id = data.getSelectedCategory().getId();
        String path = Config.API_PATH + "/v1/browse/categories/" + id + "/playlists" ;
        try {
            String response = getResponse(path, client);
            if (response.contains("error")) {
                JsonObject json = JsonParser.parseString(response).getAsJsonObject();
                JsonObject error = json.getAsJsonObject("error");
                System.out.println(error.get("message").getAsString());
                return;
            }
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonObject playlistObject = json.getAsJsonObject("playlists");
            for (JsonElement item : playlistObject.getAsJsonArray("items")) {
                JsonObject playlist = item.getAsJsonObject();
                JsonObject externalURL = playlist.getAsJsonObject("external_urls");
                String link = externalURL.get("spotify").getAsString();
                String playlistName = playlist.get("name").getAsString();
                Playlist newPlaylist = new Playlist(playlistName,link);
                data.getSelectedCategory().addPlaylist(newPlaylist);

            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
