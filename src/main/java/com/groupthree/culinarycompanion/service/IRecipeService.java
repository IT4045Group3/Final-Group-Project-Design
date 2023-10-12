package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.RecipeDTO;

public interface IRecipeService {
    void createRecipe(RecipeDTO recipeDTO);
    void updateRecipe(int recipeId, RecipeDTO recipeDTO);
    void deleteRecipe(int recipeId);
    RecipeDTO findRecipeById(int recipeId);
    RecipeDTO findRecipeByName(String name);
}
