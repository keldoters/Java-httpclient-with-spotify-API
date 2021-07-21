package com.keldoters.advisor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Auth {

    private String code;
    private final String clientID;
    private final String clientSecret;
    private String clientToken;
    private boolean authorize;


    public Auth() {
        clientSecret = System.getenv("SECRET");
        clientID = "4509ea6a545a4be0b67a136162676f8d";
        authorize = false;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void post() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Server.accessServer+"/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code"+
                                                                "&code="+code
                                                                +"&redirect_uri="+Server.redirectUri
                                                                +"&client_id="+clientID
                                                                +"&client_secret="+clientSecret))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        clientToken = json.get("access_token").getAsString();
    }

    public boolean isAuthorize() {
        return authorize;
    }
    public void authorize() {
        authorize = true;
    }

    public String getClientID() {
        return clientID;
    }
    public void setClientToken(String token) {
        clientToken = token;
    }
    public String getClientToken() {
        return clientToken;
    }
}
