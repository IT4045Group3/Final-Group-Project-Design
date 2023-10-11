package com.groupthree.culinarycompanion.dao;

public interface IRecipeCollectionDAO {
    RecipeCollection findCollectionById(long collectionId);
    List<RecipeCollection> findCollectionsByUserId(long userId);
    void createRecipeCollection(RecipeCollection collection);
    void addRecipeToCollection(long collectionId, long recipeId);
    void removeRecipeFromCollection(long collectionId, long recipeId);
    void deleteCollection(long collectionId);
}

