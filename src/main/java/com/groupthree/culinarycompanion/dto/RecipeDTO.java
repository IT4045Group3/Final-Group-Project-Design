package com.groupthree.culinarycompanion.dto;

import com.groupthree.culinarycompanion.entity.Cuisine;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class RecipeDTO {
    private int recipeId;
    private String name;
    private Cuisine cuisine;
    private String type;
    private String difficulty;
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private List<InstructionDTO> instructions = new ArrayList<>();
    private List<PhotoDTO> photos = new ArrayList<>();
    private UserDTO user = new UserDTO();
}

