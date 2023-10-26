package com.groupthree.culinarycompanion.dto;

import com.groupthree.culinarycompanion.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeCollectionDTO {
    private int collectionId;
    private int userId;
    private List<Recipe> recipes = new ArrayList<>();

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
