package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IRecipeDAO;
import com.groupthree.culinarycompanion.dao.IUserDAO;
import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService implements IRecipeService {
    private IRecipeDAO recipeDAO;
    private IUserDAO userDAO;

    public RecipeService(IRecipeDAO recipeDAO, IUserDAO userDAO) {
        this.recipeDAO = recipeDAO;
        this.userDAO = userDAO;
    }

    @Override
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = mapDTOToModel(recipeDTO);
        return mapModelToDTO(recipeDAO.createRecipe(recipe));
    }

    @Override
    public RecipeDTO updateRecipe(int recipeId, RecipeDTO recipeDTO) {
        Recipe recipe = mapDTOToModel(recipeDTO);
        recipe.setRecipeId(recipeId);
        recipeDAO.updateRecipe(recipe);
        return mapModelToDTO(recipeDAO.updateRecipe(recipe));
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
    public List<RecipeDTO> getRecipesByUserId(int userId) {
        List<Recipe> recipes = userDAO.getRecipesByUserId(userId);
        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeDTO dto = mapModelToDTO(recipe);
            recipeDTOs.add(dto);
        }
        return recipeDTOs;
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
    public List<RecipeDTO> findRecipesByNameContaining(String keyword) {
        List<Recipe> recipes = recipeDAO.findByNameContaining(keyword);
        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeDTO dto = mapModelToDTO(recipe);
            recipeDTOs.add(dto);
        }

        return recipeDTOs;
    }

    @Override
    public List<RecipeDTO> getRecipesByCategory(int categoryId) {
        List<Recipe> recipes = recipeDAO.getRecipesByCategory(categoryId);
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

        List<InstructionDTO> instructionDTOs = new ArrayList<>();
        for (Instruction instruction : recipe.getInstructions()) {
            InstructionDTO instructionDTO = new InstructionDTO();
            instructionDTO.setInstructionId(instruction.getInstructionId());
            instructionDTO.setStepNumber(instruction.getStepNumber());
            instructionDTO.setDescription(instruction.getDescription());
            instructionDTO.setVideoURL(instruction.getVideoURL());

            List<PhotoDTO> photoDTOs = new ArrayList<>();
            for (Photo photo : instruction.getPhotos()) {
                PhotoDTO photoDTO = new PhotoDTO();
                photoDTO.setPhotoId(photo.getPhotoId());
                photoDTO.setPhotoName(photo.getPhotoName());
                photoDTO.setPhotoPath(photo.getPhotoPath());
                photoDTOs.add(photoDTO);
            }
            instructionDTO.setPhotos(photoDTOs);

            instructionDTOs.add(instructionDTO);

        }
        dto.setInstructions(instructionDTOs);

        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo photo : recipe.getPhotos()) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPhotoId(photo.getPhotoId());
            photoDTO.setPhotoName(photo.getPhotoName());
            photoDTO.setPhotoPath(photo.getPhotoPath());
            photoDTOs.add(photoDTO);
        }
        dto.setPhotos(photoDTOs);

        User user = recipe.getUser();
        if (user != null) {

            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            dto.setUser(userDTO);
        }
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

        List<Instruction> instructions = new ArrayList<>();
        for (InstructionDTO instructionDTO : dto.getInstructions()) {
            Instruction instruction = new Instruction();
            instruction.setInstructionId(instructionDTO.getInstructionId());
            instruction.setStepNumber(instructionDTO.getStepNumber());
            instruction.setDescription(instructionDTO.getDescription());
            instruction.setVideoURL(instructionDTO.getVideoURL());

            List<Photo> photos = new ArrayList<>();
            for (PhotoDTO photoDTO : instructionDTO.getPhotos()) {
                Photo photo = new Photo();
                photo.setPhotoId(photoDTO.getPhotoId());
                photo.setPhotoName(photoDTO.getPhotoName());
                photo.setPhotoPath(photoDTO.getPhotoPath());
                photos.add(photo);
            }
            instruction.setPhotos(photos);

            instructions.add(instruction);
        }
        recipe.setInstructions(instructions);

        List<Photo> photos = new ArrayList<>();
        for (PhotoDTO photoDTO : dto.getPhotos()) {
            Photo photo = new Photo();
            photo.setPhotoId(photoDTO.getPhotoId());
            photo.setPhotoName(photoDTO.getPhotoName());
            photo.setPhotoPath(photoDTO.getPhotoPath());
            photos.add(photo);
        }
        recipe.setPhotos(photos);

        User user = new User();
        user.setUserId(dto.getUser().getUserId());
        user.setUsername(dto.getUser().getUsername());
        user.setEmail(dto.getUser().getEmail());
        recipe.setUser(user);

        return recipe;
    }
}
