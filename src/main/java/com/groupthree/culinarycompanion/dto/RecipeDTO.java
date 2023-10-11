package com.groupthree.culinarycompanion.dto;

import java.util.List;

public class RecipeDTO {
    private int recipeId;
    private String name;
    private String cuisine;
    private String type;
    private String difficulty;
    private List<IngredientDTO> ingredients;
    private List<InstructionDTO> instructions;
    // Getters and setters
}

