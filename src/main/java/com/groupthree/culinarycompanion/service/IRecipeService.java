package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;

import java.util.List;

public interface IRecipeService {
    void createRecipe(RecipeDTO recipeDTO);
    void updateRecipe(int recipeId, RecipeDTO recipeDTO);
    void deleteRecipe(int recipeId);
    RecipeDTO findRecipeById(int recipeId);
    RecipeDTO findRecipeByName(String name);
    List<RecipeDTO> getAllRecipes();
    RecipeDTO mapModelToDTO(Recipe recipe);
    Recipe mapDTOToModel(RecipeDTO dto);
}

