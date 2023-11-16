package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.Ingredient;

import java.io.IOException;
import java.util.List;

public interface IIngredientService {
    Ingredient createIngredient(String name);
    void createIngredientsFromTextarea(String textareaContent);
    List<Ingredient> getAllIngredients();
    void addCommonIngredientsFromJson() throws IOException;
}
