package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.Config;
import com.keldoters.advisor.DataManager;
import com.keldoters.advisor.resource.Album;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class GetNewRelease implements API {

    public void get(DataManager data, Auth client) {
        try {
            String response = getResponse(Config.API_PATH + Config.NEW_RELEASE, client);
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonObject newReleaseObject = json.getAsJsonObject("albums");
            for (JsonElement item : newReleaseObject.getAsJsonArray("items")) {
                JsonObject album = item.getAsJsonObject();
                String albumName = album.get("name").getAsString();
                JsonObject externalUrl = album.getAsJsonObject("external_urls");
                String link = externalUrl.get("spotify").getAsString();
                String[] array = new String[album.getAsJsonArray("artists").size()];
                int i = 0;
                for (JsonElement artist : album.getAsJsonArray("artists")) {
                    JsonObject artistInfo = artist.getAsJsonObject();
                    array[i] = artistInfo.get("name").getAsString();
                    i++;
                }
                Album albumObj = new Album(albumName, array, link);
                data.addNewRelease(albumObj);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
