package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int ingredientId;
    private String name;
    @ManyToOne
    private Recipe recipe;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

