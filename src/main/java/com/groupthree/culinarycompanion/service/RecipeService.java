package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IRecipeDAO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import com.groupthree.culinarycompanion.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeDAO.findAllRecipes();
        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeDTO dto = mapModelToDTO(recipe);
            recipeDTOs.add(dto);
        }

        return recipeDTOs;
    }


    @Override
    public RecipeDTO mapModelToDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipeId(recipe.getRecipeId());
        dto.setName(recipe.getName());
        dto.setCuisine(recipe.getCuisine());
        dto.setType(recipe.getType());
        dto.setDifficulty(recipe.getDifficulty());

        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo photo : recipe.getPhotos()) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPhotoId(photo.getPhotoId());
            photoDTO.setPhotoName(photo.getPhotoName());
            photoDTO.setPhotoPath(photo.getPhotoPath());
            photoDTOs.add(photoDTO);
        }
        dto.setPhotos(photoDTOs);

        return dto;
    }

    @Override
    public Recipe mapDTOToModel(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(dto.getRecipeId());
        recipe.setName(dto.getName());
        recipe.setCuisine(dto.getCuisine());
        recipe.setType(dto.getType());
        recipe.setDifficulty(dto.getDifficulty());

        List<Photo> photos = new ArrayList<>();
        for (PhotoDTO photoDTO : dto.getPhotos()) {
            Photo photo = new Photo();
            photo.setPhotoId(photoDTO.getPhotoId());
            photo.setPhotoName(photoDTO.getPhotoName());
            photo.setPhotoPath(photoDTO.getPhotoPath());
            photos.add(photo);
        }
        recipe.setPhotos(photos);

        return recipe;
    }
}
