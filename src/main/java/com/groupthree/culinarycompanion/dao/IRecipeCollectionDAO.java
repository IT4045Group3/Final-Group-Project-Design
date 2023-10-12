package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.RecipeCollection;

import java.util.List;

public interface IRecipeCollectionDAO {
    RecipeCollection findCollectionById(int collectionId);
    List<RecipeCollection> findCollectionsByUserId(int userId);
    void createCollection(RecipeCollection collection);
    void addRecipeToCollection(int collectionId, int recipeId);
    void removeRecipeFromCollection(int collectionId, int recipeId);
    void deleteCollection(int collectionId);
}

