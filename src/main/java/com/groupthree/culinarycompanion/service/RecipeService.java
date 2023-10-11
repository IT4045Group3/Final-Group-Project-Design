package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IRecipeDAO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;

public class RecipeService implements IRecipeService {
    private IRecipeDAO recipeDAO;

    public RecipeService(IRecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Override
    public void createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = mapDTOToModel(recipeDTO);
        recipeDAO.createRecipe(recipe);
    }

    @Override
    public void updateRecipe(int recipeId, RecipeDTO recipeDTO) {
        Recipe recipe = mapDTOToModel(recipeDTO);
        recipe.setRecipeId(recipeId);
        recipeDAO.updateRecipe(recipe);
    }

    @Override
    public void deleteRecipe(int recipeId) {
        recipeDAO.deleteRecipe(recipeId);
    }

    @Override
    public RecipeDTO findRecipeById(int recipeId) {
        Recipe recipe = recipeDAO.findRecipeById(recipeId);
        return mapModelToDTO(recipe);
    }

    @Override
    public RecipeDTO findRecipeByName(String name) {
        Recipe recipe = recipeDAO.findRecipeByName(name);
        return mapModelToDTO(recipe);
    }

    private RecipeDTO mapModelToDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipeId(recipe.getRecipeId());
        dto.setName(recipe.getName());
        dto.setCuisine(recipe.getCuisine());
        dto.setType(recipe.getType());
        dto.setDifficulty(recipe.getDifficulty());
        return dto;
    }

    private Recipe mapDTOToModel(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(dto.getRecipeId());
        recipe.setName(dto.getName());
        recipe.setCuisine(dto.getCuisine());
        recipe.setType(dto.getType());
        recipe.setDifficulty(dto.getDifficulty());
        return recipe;
    }
}
