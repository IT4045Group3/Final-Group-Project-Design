package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Ingredient;
import com.groupthree.culinarycompanion.repository.IngredientRepository;
import com.groupthree.culinarycompanion.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IIngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient createIngredient(String name) {
        String upperCaseName = name.trim().toUpperCase();
        Ingredient existingIngredient = ingredientRepository.findByName(upperCaseName);
        if (existingIngredient != null) {
            return existingIngredient;
        }
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(upperCaseName);
        return ingredientRepository.save(newIngredient);
    }


    @Override
    public void createIngredientsFromTextarea(String textareaContent) {
        String[] ingredientNameArray = textareaContent.split("\n");
        for (String name : ingredientNameArray) {
            createIngredient(name.trim().toUpperCase());
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
