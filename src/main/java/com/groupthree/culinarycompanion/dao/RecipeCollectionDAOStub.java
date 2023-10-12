package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.RecipeCollection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeCollectionDAOStub implements IRecipeCollectionDAO {
    private List<RecipeCollection> collectionDatabase = new ArrayList<>();
    private int nextCollectionId = 1;

    @Override
    public RecipeCollection findCollectionById(int collectionId) {
        for (RecipeCollection collection : collectionDatabase) {
            if (collection.getCollectionId() == collectionId) {
                return collection;
            }
        }
        return null;
    }

    @Override
    public List<RecipeCollection> findCollectionsByUserId(int userId) {
        List<RecipeCollection> userCollections = new ArrayList<>();
        for (RecipeCollection collection : collectionDatabase) {
            if (collection.getUserId() == userId) {
                userCollections.add(collection);
            }
        }
        return userCollections;
    }

    @Override
    public void createCollection(RecipeCollection collection) {
        collection.setCollectionId(nextCollectionId);
        nextCollectionId++;
        collectionDatabase.add(collection);
    }

    @Override
    public void addRecipeToCollection(int collectionId, int recipeId) {
        RecipeCollection collection = findCollectionById(collectionId);
        if (collection != null) {
            collection.getRecipeIds().add(recipeId);
        }
    }

    @Override
    public void removeRecipeFromCollection(int collectionId, int recipeId) {
        RecipeCollection collection = findCollectionById(collectionId);
        if (collection != null) {
            collection.getRecipeIds().remove(Integer.valueOf(recipeId));
        }
    }

    @Override
    public void deleteCollection(int collectionId) {
        RecipeCollection collectionToRemove = null;
        for (RecipeCollection collection : collectionDatabase) {
            if (collection.getCollectionId() == collectionId) {
                collectionToRemove = collection;
                break;
            }
        }
        if (collectionToRemove != null) {
            collectionDatabase.remove(collectionToRemove);
        }
    }
}
