package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.repository.RecipeCollectionRepository;
import com.groupthree.culinarycompanion.dto.RecipeCollectionDTO;
import com.groupthree.culinarycompanion.entity.RecipeCollection;
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
    public void createCollection(RecipeCollection collection) {
        recipeCollectionRepository.save(collection);
    }

    @Override
    public void addRecipeToCollection(int collectionId, int recipeId) {
        RecipeCollection collection = recipeCollectionRepository.findById(collectionId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (collection != null && recipe != null) {
            collection.getRecipes().add(recipe);
            recipe.setRecipeCollection(collection);
            recipeCollectionRepository.save(collection);
        }
    }

    @Override
    public void removeRecipeFromCollection(int collectionId, int recipeId) {
        RecipeCollection collection = recipeCollectionRepository.findById(collectionId).orElse(null);

        if (collection != null) {
            collection.getRecipes().removeIf(recipe -> recipe.getRecipeId() == recipeId);
            recipeCollectionRepository.save(collection);
        }
    }

    @Override
    public RecipeCollection findCollectionById(int collectionId) {
        return recipeCollectionRepository.findById(collectionId).orElse(null);
    }

    @Override
    public List<RecipeCollection> findCollectionsByUserId(int userId) {
        return recipeCollectionRepository.findByUserId(userId);
    }
}
