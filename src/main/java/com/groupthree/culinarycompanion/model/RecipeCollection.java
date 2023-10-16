package com.groupthree.culinarycompanion.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

public class RecipeCollection {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int collectionId;
    private int userId;
    private List<Integer> recipeIds;
    // Getters and setters

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

    public List<Integer> getRecipeIds() {
        return recipeIds;
    }

    public void setRecipeIds(List<Integer> recipeIds) {
        this.recipeIds = recipeIds;
    }
}

