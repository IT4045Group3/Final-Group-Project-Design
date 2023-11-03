package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.repository.RecipeCollectionRepository;
import com.groupthree.culinarycompanion.entity.Collection;
import com.groupthree.culinarycompanion.repository.RecipeRepository;
import com.groupthree.culinarycompanion.service.IRecipeCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeCollectionServiceImpl implements IRecipeCollectionService {

    @Autowired
    private RecipeCollectionRepository recipeCollectionRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public void createRecipeCollection(Collection collection) {
        recipeCollectionRepository.save(collection);
    }

    @Override
    public Collection getRecipeCollectionById(int collectionId) {
        return recipeCollectionRepository.findById(collectionId).orElse(null);
    }

    @Override
    public List<Collection> getRecipeCollectionsByUserId(int userId) {
        return recipeCollectionRepository.findByUserId(userId);
    }

    @Override
    public void addRecipeToCollection(Collection collection, Recipe recipe) {
        recipe.getCollections().add(collection);
        recipeRepository.save(recipe);
    }

    @Override
    public Collection removeRecipeFromCollection(Collection collection, Recipe recipe) {
        collection.getRecipes().remove(recipe);
        return recipeCollectionRepository.save(collection);
    }

    @Override
    public void deleteRecipeCollection(int collectionId) {
        recipeCollectionRepository.deleteById(collectionId);
    }

}
