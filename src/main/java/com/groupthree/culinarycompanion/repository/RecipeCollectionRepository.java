package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.RecipeCollection;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeCollectionRepository {
    RecipeCollection findCollectionById(int collectionId);
    List<RecipeCollection> findCollectionsByUserId(int userId);
    void createCollection(RecipeCollection collection);
    void addRecipeToCollection(int collectionId, int recipeId);
    void removeRecipeFromCollection(int collectionId, int recipeId);
    void deleteCollection(int collectionId);
}

