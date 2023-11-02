package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int ingredientId;
    private String name;
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

