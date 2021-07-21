package com.keldoters.advisor;

import com.keldoters.advisor.resource.Album;
import com.keldoters.advisor.resource.Category;
import com.keldoters.advisor.resource.Playlist;
import java.util.ArrayList;



public class DataManager {
    private ArrayList<Category> categories;
    private ArrayList<Album> newRelease;
    private ArrayList<Playlist> featured;
    private Category selectedCategory;

    public DataManager() {
        categories = new ArrayList<>();
        newRelease = new ArrayList<>();
        featured = new ArrayList<>();
    }

    public ArrayList<Album> getNewRelease() {
        return newRelease;
    }

    public ArrayList<Playlist> getFeatured() {
        return featured;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
    public void addCategory(Category newCategory) {
        if (!categories.contains(newCategory)) {
            categories.add(newCategory);
        }
    }
    public void addNewRelease(Album album) {
        if (!newRelease.contains(album)) {
            newRelease.add(album);
        }
    }
    public void addFeatured(Playlist playlist) {
        if (!featured.contains(playlist)) {
            featured.add(playlist);
        }
    }
    public boolean selectCategory(String name) {
        selectedCategory = null;
        for (Category obj : categories) {
            if (name.equals(obj.getName())) {
                selectedCategory = obj;
                return true;
            }
        }
        return false;
    }
    public Category getSelectedCategory() {
        return selectedCategory;
    }
    public void printCategories() {
        for (Category obj : categories) {
            System.out.println(obj);
        }
    }
    public void printNewRelease() {
        for (Album obj : newRelease) {
            System.out.println(obj);
        }
    }
    public void printFeatured() {
        for (Playlist obj : featured) {
            System.out.println(obj + "\n");
        }
    }
    public void printCategoryPlaylist() {
        if (selectedCategory != null) {
            selectedCategory.printPlaylists();
        }
    }


}
