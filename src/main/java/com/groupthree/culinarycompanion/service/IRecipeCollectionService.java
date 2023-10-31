package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.RecipeCollection;

import java.util.List;

public interface IRecipeCollectionService {
    void createCollection(RecipeCollection collection);
    void addRecipeToCollection(int collectionId, int recipeId);
    void removeRecipeFromCollection(int collectionId, int recipeId);
    RecipeCollection findCollectionById(int collectionId);
    List<RecipeCollection> findCollectionsByUserId(int userId);
}

