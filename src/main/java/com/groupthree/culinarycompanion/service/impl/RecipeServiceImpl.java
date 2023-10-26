package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.repository.PhotoRepository;
import com.groupthree.culinarycompanion.repository.RecipeRepository;
import com.groupthree.culinarycompanion.repository.UserRepository;
import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.entity.*;
import com.groupthree.culinarycompanion.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(int recipeId, Recipe updatedRecipe) {
        Recipe existingRecipe = findRecipeById(recipeId);
        if (existingRecipe != null) {
            updatedRecipe.setRecipeId(recipeId);
            return recipeRepository.save(updatedRecipe);
        }
        return null;
    }

    @Override
    public void deleteRecipe(int recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public void addPhotoInRecipe(int recipeId, String imagePath, String imageName) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            Photo photo = new Photo();
            photo.setPhotoPath(imagePath);
            photo.setPhotoName(imageName);
            photo.setRecipe(recipe);
            photoRepository.save(photo);
        }
    }

    @Override
    public Recipe findRecipeById(int recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }

    @Override
    public List<Recipe> getRecipesByUserId(int userId) {
        return recipeRepository.findByUserUserId(userId);
    }

    @Override
    public Recipe findRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> findRecipesByNameContaining(String keyword) {
        return recipeRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Recipe> getRecipesByCategory(int categoryId) {
        return recipeRepository.findByCuisineId(categoryId);
    }
}
