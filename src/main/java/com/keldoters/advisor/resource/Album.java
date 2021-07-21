package com.keldoters.advisor.resource;

import java.util.Arrays;
import java.util.Objects;

public class Album implements SpotifyData{

    private String name;
    private String[] artists;
    private String link;

    public Album(String name, String[] artists, String link) {
        this.name = name;
        this.artists = artists;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String[] getArtists() {
        return artists;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(getName(), album.getName()) && Arrays.equals(getArtists(), album.getArtists()) && Objects.equals(getLink(), album.getLink());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getLink());
        result = 31 * result + Arrays.hashCode(getArtists());
        return result;
    }
    public String toString() {
        return name + "\n" + Arrays.toString(artists) + "\n" + link + "\n";
    }


}
