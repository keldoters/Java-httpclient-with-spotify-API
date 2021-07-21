package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.DataManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface API {

    default String getResponse(String path, Auth user) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + user.getClientToken())
                .uri(URI.create(path))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    void get(DataManager data, Auth client);
}
