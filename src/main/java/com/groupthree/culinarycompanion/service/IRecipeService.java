package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;

import java.util.List;

public interface IRecipeService {
    RecipeDTO createRecipe(RecipeDTO recipeDTO);
    RecipeDTO updateRecipe(int recipeId, RecipeDTO recipeDTO);
    void deleteRecipe(int recipeId);
    RecipeDTO findRecipeById(int recipeId);
    RecipeDTO findRecipeByName(String name);
    List<RecipeDTO> getAllRecipes();
    List<RecipeDTO> findRecipesByNameContaining(String keyword);
    List<RecipeDTO> getRecipesByCategory(int categoryId);
    List<RecipeDTO> getRecipesByUserId(int userId);
    RecipeDTO addInstructionToRecipe(int recipeId, InstructionDTO newInstruction);
    public RecipeDTO updateInstructionInRecipe(int recipeId, int instructionId, InstructionDTO updatedInstruction);
    public RecipeDTO removeInstructionFromRecipe(int recipeId, int instructionId);
    RecipeDTO mapModelToDTO(Recipe recipe);
    Recipe mapDTOToModel(RecipeDTO dto);
}

