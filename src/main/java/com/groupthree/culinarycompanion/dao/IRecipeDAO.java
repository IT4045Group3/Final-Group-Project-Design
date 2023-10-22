package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.Instruction;
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
    Instruction addInstructionToRecipe(int recipeId, Instruction instruction);
    void deletePhotoToInstruction(int recipeId, int instructionId, int photoId);
    void removeInstructionFromRecipe(int recipeId, int instructionId);
    void updateInstructionToRecipe(int recipeId, int instructionId, Instruction updatedInstruction);
    void addPhotoInInstruction(int recipeId, int instructionId, String imagePath);
}

