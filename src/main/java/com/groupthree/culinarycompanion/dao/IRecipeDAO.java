package com.groupthree.culinarycompanion.dao;

public interface IRecipeDAO {
    Recipe findRecipeById(long recipeId);
    Recipe findRecipeByName(String name);
    void createRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(long recipeId);
}

