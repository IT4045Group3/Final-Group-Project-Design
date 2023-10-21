package com.groupthree.culinarycompanion.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

public class Recipe {

    @Getter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int recipeId;
    @Getter
    private String name;
    @Getter
    @ManyToOne
    private CuisineCategory cuisine; //ex: Mexican cuisine, Italian Cuisine, Chinese cuisine, etc

    @Getter
    private String type;

    @Getter
    private String difficulty;

    private List<Ingredient> ingredients;

    private List<Instruction> instructions;

    @Getter
    private List<Photo> photos;

    @ManyToOne
    private User user;

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(CuisineCategory cuisine) {
        this.cuisine = cuisine;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
