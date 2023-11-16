package com.groupthree.culinarycompanion.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupthree.culinarycompanion.entity.Ingredient;
import com.groupthree.culinarycompanion.repository.IngredientRepository;
import com.groupthree.culinarycompanion.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    public void addCommonIngredientsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("static/ingredients.json");
        Ingredient[] commonIngredients = objectMapper.readValue(resource.getInputStream(), Ingredient[].class);

        for (Ingredient commonIngredient : commonIngredients) {
            Ingredient existingIngredient = ingredientRepository.findByName(commonIngredient.getName());

            if (existingIngredient == null) {
                ingredientRepository.save(commonIngredient);
            }
        }
    }
}
