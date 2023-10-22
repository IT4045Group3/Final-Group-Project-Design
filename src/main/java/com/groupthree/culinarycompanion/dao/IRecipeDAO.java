package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;

import java.util.List;

public interface IRecipeDAO {
    Recipe findRecipeById(int recipeId);
    Recipe findRecipeByName(String name);
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(int recipeId);
    List<Recipe> findAllRecipes();
    List<Recipe> findByNameContaining(String keyword);
    List<Recipe> getRecipesByCategory(int categoryId);
}

