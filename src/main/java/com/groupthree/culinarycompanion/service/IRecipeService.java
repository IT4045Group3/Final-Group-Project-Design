package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.entity.Recipe;

import java.util.List;

public interface IRecipeService {
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipe(int recipeId, Recipe recipe);
    void deleteRecipe(int recipeId);
    void addPhotoInRecipe(int recipeId, String imagePath, String imageName);
    void updatePhotoInRecipe(int recipeId, String imagePath, String imageName);
    Recipe findRecipeById(int recipeId);
    List<Recipe> getRecipesByUserId(int userId);
    Recipe findRecipeByName(String name);
    List<Recipe> getAllRecipes();
    List<Recipe> findRecipesByNameContaining(String keyword);
    List<Recipe> getRecipesByCategory(int categoryId);
}

