package com.groupthree.culinarycompanion.dto;

import java.util.List;

public class RecipeCollectionDTO {
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
