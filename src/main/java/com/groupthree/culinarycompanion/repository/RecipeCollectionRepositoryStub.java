package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.RecipeCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeCollectionRepositoryStub implements RecipeCollectionRepository {
    private List<RecipeCollection> collectionDatabase = new ArrayList<>();
    private int nextCollectionId = 1;

    @Autowired
    RecipeRepositoryStub recipeRepositoryStub;

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
            Recipe recipeToAdd = recipeRepositoryStub.findRecipeById(recipeId);
            collection.getRecipes().add(recipeToAdd);
        }
    }

    @Override
    public void removeRecipeFromCollection(int collectionId, int recipeId) {
        RecipeCollection collection = findCollectionById(collectionId);
        if (collection != null) {
            collection.getRecipes().removeIf(recipe -> recipe.getRecipeId() == recipeId);
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
