package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;

import java.util.List;

public interface IRecipeDAO {
    Recipe findRecipeById(int recipeId);
    Recipe findRecipeByName(String name);
    void createRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(int recipeId);
    List<Recipe> findAllRecipes();
}

