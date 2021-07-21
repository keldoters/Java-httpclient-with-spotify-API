package com.keldoters.advisor;

import com.keldoters.advisor.resource.SpotifyData;
import java.util.ArrayList;

public class Controller {

    private int perPage = Config.PAGE;
    private int totalPages;
    private ArrayList<? extends SpotifyData> list;
    private int counter;
    private int currentPage;

    public void setData(ArrayList<? extends SpotifyData> list) {
        this.list = list;
        counter = 0;
        currentPage = 1;
        if (list.size() % perPage != 0) {
            totalPages = list.size() / perPage + 1;
        } else {
            totalPages = list.size() / perPage;
        }

    }

    public void printPage() {
        for (int i = counter; i < counter + perPage; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("---PAGE "+currentPage+" OF "+totalPages+"---");
    }
    public void prev() {
        if (currentPage == 1) {
            System.out.println("No more pages.");
        } else {
            currentPage--;
            counter -= perPage;
            printPage();
        }
    }
    public void next() {
        if (currentPage == totalPages) {
            System.out.println("No more pages.");
        } else {
            currentPage++;
            counter += perPage;
            printPage();
        }
    }



}
