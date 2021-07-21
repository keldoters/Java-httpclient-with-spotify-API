package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.Config;
import com.keldoters.advisor.DataManager;
import com.keldoters.advisor.resource.Category;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class GetCategory implements API {

    public void get(DataManager data, Auth client) {
        try {
            String response = getResponse(Config.API_PATH + Config.CATEGORIES, client);
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonObject categoriesObject = json.getAsJsonObject("categories");
            for (JsonElement items : categoriesObject.getAsJsonArray("items")) {
                JsonObject category = items.getAsJsonObject();
                String id = category.get("id").getAsString();
                String name = category.get("name").getAsString();
                Category newCategory = new Category(name, id);
                data.addCategory(newCategory);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
