package com.groupthree.culinarycompanion.dto;

import com.groupthree.culinarycompanion.model.Recipe;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class UserDTO {
    private int userId;
    private String username;
    private String email;
    private String password;
    private List<RecipeDTO> recipes = new ArrayList<>();

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }
}
