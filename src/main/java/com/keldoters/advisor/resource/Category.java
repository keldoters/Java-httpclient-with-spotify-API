package com.keldoters.advisor.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Category implements SpotifyData {
    private String name;
    private String id;
    private ArrayList<Playlist> playlists;

    public Category(String name, String id) {
        this.name = name;
        this.id = id;
        this.playlists = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getName(), category.getName()) && Objects.equals(getId(), category.getId()) && Objects.equals(playlists, category.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), playlists);
    }



    public void addPlaylist(Playlist playlist) {
        if (!playlists.contains(playlist)) {
            playlists.add(playlist);
        }
    }

    public void printPlaylists() {
        for (Playlist per : playlists) {
            System.out.println(per+"\n");
        }

    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
