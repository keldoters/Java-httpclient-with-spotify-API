package com.keldoters.advisor.SpotifyAPI;

import com.keldoters.advisor.Auth;
import com.keldoters.advisor.DataManager;

public class DataDownloader {

    private API downloader;

    public void setMethod(API getMethod) {
        downloader = getMethod;
    }
    public void getData(DataManager data, Auth client) {
        downloader.get(data,client);
    }
}
