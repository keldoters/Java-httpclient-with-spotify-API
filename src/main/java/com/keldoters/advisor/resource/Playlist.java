package com.keldoters.advisor.resource;

import java.util.Objects;

public class Playlist implements SpotifyData{

    private String link;
    private String name;

    public Playlist(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return link.equals(playlist.link) && name.equals(playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, name);
    }
    public String toString() {
        return this.name + "\n" + this.link;
    }
}
