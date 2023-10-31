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
            if (updatedRecipe.getName() != null && !existingRecipe.getName().equals(updatedRecipe.getName())) {
                existingRecipe.setName(updatedRecipe.getName());
            }
            if (updatedRecipe.getCuisine() != null && !existingRecipe.getCuisine().equals(updatedRecipe.getCuisine())) {
                existingRecipe.setCuisine(updatedRecipe.getCuisine());
            }
            if (updatedRecipe.getType() != null && !existingRecipe.getType().equals(updatedRecipe.getType())) {
                existingRecipe.setType(updatedRecipe.getType());
            }
            if (updatedRecipe.getDifficulty() != null && !existingRecipe.getDifficulty().equals(updatedRecipe.getDifficulty())) {
                existingRecipe.setDifficulty(updatedRecipe.getDifficulty());
            }
            if (updatedRecipe.getIngredients() != null && !existingRecipe.getIngredients().equals(updatedRecipe.getIngredients())) {
                existingRecipe.setIngredients(updatedRecipe.getIngredients());
            }
            if (updatedRecipe.getPhotos() != null && !existingRecipe.getPhotos().equals(updatedRecipe.getPhotos())) {
                existingRecipe.setPhotos(updatedRecipe.getPhotos());
            }
            if (updatedRecipe.getRecipeCollection() != null && !existingRecipe.getRecipeCollection().equals(updatedRecipe.getRecipeCollection())) {
                existingRecipe.setRecipeCollection(updatedRecipe.getRecipeCollection());
            }
            if (updatedRecipe.getUser() != null && !existingRecipe.getUser().equals(updatedRecipe.getUser())) {
                existingRecipe.setUser(updatedRecipe.getUser());
            }
            if (updatedRecipe.getInstructions() != null && !existingRecipe.getInstructions().equals(updatedRecipe.getInstructions())) {
                existingRecipe.setInstructions(updatedRecipe.getInstructions());
            }
            return recipeRepository.save(existingRecipe);
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
    public void updatePhotoInRecipe(int recipeId, String imagePath, String imageName) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            Photo photo = photoRepository.findByRecipeRecipeId(recipeId);

            if (photo != null) {
                photo.setPhotoPath(imagePath);
                photo.setPhotoName(imageName);
                photoRepository.save(photo);
            }
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
