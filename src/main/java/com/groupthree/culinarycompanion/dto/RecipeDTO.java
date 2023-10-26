package com.groupthree.culinarycompanion.dto;

import com.groupthree.culinarycompanion.entity.CuisineCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class RecipeDTO {
    private int recipeId;
    private String name;
    private CuisineCategory cuisine;
    private String type;
    private String difficulty;
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private List<InstructionDTO> instructions = new ArrayList<>();
    private List<PhotoDTO> photos = new ArrayList<>();
    private UserDTO user = new UserDTO();

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CuisineCategory getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineCategory cuisine) {
        this.cuisine = cuisine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<InstructionDTO> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionDTO> instructions) {
        this.instructions = instructions;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

