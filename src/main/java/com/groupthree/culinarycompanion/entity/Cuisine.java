package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int cuisineId;
    private String name;
    @OneToMany(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @OneToMany(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public int getId() {
        return cuisineId;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
