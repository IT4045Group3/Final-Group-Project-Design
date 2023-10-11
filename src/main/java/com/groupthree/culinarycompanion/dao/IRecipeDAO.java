package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.Recipe;

public interface IRecipeDAO {
    Recipe findRecipeById(int recipeId);
    Recipe findRecipeByName(String name);
    void createRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(int recipeId);
}

