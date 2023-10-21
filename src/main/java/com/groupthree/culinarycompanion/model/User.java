package com.groupthree.culinarycompanion.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int userId;
    @Getter
    private String username;
    @Getter
    private String email;
    @Getter
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

    // Getters and setters

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
