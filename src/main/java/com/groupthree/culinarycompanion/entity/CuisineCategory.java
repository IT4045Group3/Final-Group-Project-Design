package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.*;

import javax.management.loading.PrivateClassLoader;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class CuisineCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "cuisineCategory", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @OneToOne(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private Recipe recipe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
