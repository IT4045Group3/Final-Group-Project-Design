package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.Collection;

import java.util.List;

public interface IRecipeCollectionService {
    void createRecipeCollection(Collection collection);
    Collection getRecipeCollectionById(int collectionId);
    List<Collection> getRecipeCollectionsByUserId(int userId);
    void addRecipeToCollection(Collection collection, Recipe recipe);
    Collection removeRecipeFromCollection(Collection collection, Recipe recipe);
    void deleteRecipeCollection(int collectionId);

}

