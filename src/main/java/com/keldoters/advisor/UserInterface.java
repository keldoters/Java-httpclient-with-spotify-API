package com.keldoters.advisor;

import com.keldoters.advisor.SpotifyAPI.*;
import com.keldoters.advisor.resource.Category;
import com.keldoters.advisor.resource.Playlist;
import com.keldoters.advisor.resource.SpotifyData;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private DataDownloader downloader;
    private DataManager data;
    private Scanner scanner;
    private Auth client;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.downloader = new DataDownloader();
        this.data = new DataManager();
        this.client = new Auth();
        this.controller = new Controller();
    }
    public void start() {
        boolean state = true;
        try {
            while (state) {
                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    state = false;
                }
                if (!client.isAuthorize()) {
                    if (input.equals("auth")) {
                        getAccessCode();
                        downloader.setMethod(new GetCategory());
                        downloader.getData(data, client);
                    } else {
                        System.out.println("Please, provide access for application.");
                        continue;
                    }
                }
                switch (input) {
                    case "new":
                        printNewRelease();
                        break;
                    case "featured":
                        printFeatured();
                        break;
                    case "categories":
                        printCategories();
                        break;
                    case "prev" :
                        controller.prev();
                        break;
                    case "next" :
                        controller.next();
                        break;
                    default:
                        if (input.contains("playlists ")) {
                            printPlaylists(input);
                        }
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getAccessCode() throws IOException, InterruptedException {
        Server.setLocalServer();
        System.out.println("use this link to request the access code:");
        System.out.println("https://accounts.spotify.com/authorize?client_id=" + client.getClientID()
                + "&redirect_uri=" + Server.redirectUri
                + "&response_type=code");
        System.out.println("waiting for code...");
        Server.getAccessCode(client);
        while (client.getCode() == null) {
            Thread.sleep(10);
        }
        Server.stopServer();
        System.out.println("code received");
        System.out.println("making http request for access_token...");
        client.post();
        System.out.println("Success !");
        client.authorize();
    }
    public void printCategories() {
        controller.setData(data.getCategories());
        controller.printPage();
    }

    public void printFeatured() {
        downloader.setMethod(new GetFeatured());
        downloader.getData(data, client);
        controller.setData(data.getFeatured());
        controller.printPage();

    }

    public void printNewRelease() {
        downloader.setMethod(new GetNewRelease());
        downloader.getData(data, client);
        controller.setData(data.getNewRelease());
        controller.printPage();

    }

    public void printPlaylists(String input) {
        StringBuilder name = new StringBuilder(input);
        name.delete(0,10);
        if (data.selectCategory(name.toString())) {
            downloader.setMethod(new GetPlaylists());
            downloader.getData(data, client);
            controller.setData(data.getSelectedCategory().getPlaylists());
            controller.printPage();
        } else {
            System.out.println("Unknown category name.");
        }
    }

}


