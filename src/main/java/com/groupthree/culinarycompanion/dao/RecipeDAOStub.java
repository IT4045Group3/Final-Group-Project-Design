package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAOStub implements IRecipeDAO {
    private List<Recipe> recipeDatabase = new ArrayList<>();
    private int nextRecipeId = 1;

    @Override
    public Recipe findRecipeById(int recipeId) {
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getRecipeId() == recipeId) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public Recipe findRecipeByName(String name) {
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void createRecipe(Recipe recipe) {
        recipe.setRecipeId(nextRecipeId);
        nextRecipeId++;
        recipeDatabase.add(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        int index = -1;
        for (int i = 0; i < recipeDatabase.size(); i++) {
            if (recipeDatabase.get(i).getRecipeId() == recipe.getRecipeId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            recipeDatabase.set(index, recipe);
        }
    }

    @Override
    public void deleteRecipe(int recipeId) {
        Recipe recipeToRemove = null;
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getRecipeId() == recipeId) {
                recipeToRemove = recipe;
                break;
            }
        }
        if (recipeToRemove != null) {
            recipeDatabase.remove(recipeToRemove);
        }
    }
}
