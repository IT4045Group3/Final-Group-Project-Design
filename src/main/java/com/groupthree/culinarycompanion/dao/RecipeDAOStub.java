package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Photo;
import com.groupthree.culinarycompanion.model.Recipe;
import com.groupthree.culinarycompanion.service.RecipeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDAOStub implements IRecipeDAO {
    private List<Recipe> recipeDatabase = new ArrayList<>();
    private int nextRecipeId = 1;

    @Override
    public Recipe findRecipeById(int recipeId) {
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getRecipeId() == recipeId) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public Recipe findRecipeByName(String name) {
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void createRecipe(Recipe recipe) {
        recipe.setRecipeId(nextRecipeId);
        nextRecipeId++;
        recipeDatabase.add(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        int index = -1;
        for (int i = 0; i < recipeDatabase.size(); i++) {
            if (recipeDatabase.get(i).getRecipeId() == recipe.getRecipeId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            recipeDatabase.set(index, recipe);
        }
    }

    @Override
    public void deleteRecipe(int recipeId) {
        Recipe recipeToRemove = null;
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getRecipeId() == recipeId) {
                recipeToRemove = recipe;
                break;
            }
        }
        if (recipeToRemove != null) {
            recipeDatabase.remove(recipeToRemove);
        }
    }

    @Override
    public List<String> findAllCuisineCategories() {
        List<String> cuisineCategories = new ArrayList<>();

        for (Recipe recipe : recipeDatabase) {
            String cuisine = recipe.getCuisine();
            if (!cuisineCategories.contains(cuisine)) {
                cuisineCategories.add(cuisine);
            }
        }

        return cuisineCategories;
    }

    @Override
    public List<RecipeDTO> findAllRecipes() {
        List<RecipeDTO> recipeDTOs = new ArrayList<>();
        for (Recipe recipe : recipeDatabase) {
            RecipeDTO recipeDTO = RecipeService.mapModelToDTO(recipe);
            recipeDTOs.add(recipeDTO);
        }
        return recipeDTOs;
    }

    @PostConstruct
    public void initDefaultRecipes() {

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(nextRecipeId++);
        recipe1.setName("Taco");
        recipe1.setCuisine("Mexican");
        recipe1.setType("Main Dish");

        List<Photo> photos1 = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setPhotoId(1);
        photo1.setPhotoName("Taco Photo 1");
        photo1.setPhotoPath("taco-photo-1.jpg");
        photos1.add(photo1);

        Photo photo2 = new Photo();
        photo2.setPhotoId(2);
        photo2.setPhotoName("Taco Photo 2");
        photo2.setPhotoPath("taco-photo-2.jpg");
        photos1.add(photo2);

        recipe1.setPhotos(photos1);

        recipeDatabase.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(nextRecipeId++);
        recipe2.setName("Spaghetti Carbonara");
        recipe2.setCuisine("Italian");
        recipe2.setType("Main Dish");

        List<Photo> photos2 = new ArrayList<>();
        Photo photo3 = new Photo();
        photo3.setPhotoId(1);
        photo3.setPhotoName("Spaghetti Carbonara Photo 1");
        photo3.setPhotoPath("spaghetti-carbonara-photo-1.jpg");
        photos2.add(photo3);

        Photo photo4 = new Photo();
        photo4.setPhotoId(2);
        photo4.setPhotoName("Spaghetti Carbonara Photo 2");
        photo4.setPhotoPath("spaghetti-carbonara-photo-2.jpg");
        photos2.add(photo4);

        recipe2.setPhotos(photos2);

        recipeDatabase.add(recipe2);

        Recipe recipe3 = new Recipe();
        recipe3.setRecipeId(nextRecipeId++);
        recipe3.setName("Jiaozi");
        recipe3.setCuisine("Chinese");
        recipe3.setType("Main Dish");

        List<Photo> photos3 = new ArrayList<>();
        Photo photo5 = new Photo();
        photo5.setPhotoId(1);
        photo5.setPhotoName("Jiaozi Photo 1");
        photo5.setPhotoPath("jiaozi-photo-1.jpg");
        photos3.add(photo5);

        Photo photo6 = new Photo();
        photo6.setPhotoId(2);
        photo6.setPhotoName("Jiaozi Photo 2");
        photo6.setPhotoPath("jiaozi-photo-2.jpg");
        photos3.add(photo6);

        recipe3.setPhotos(photos3);

        recipeDatabase.add(recipe3);

        Recipe recipe4 = new Recipe();
        recipe4.setRecipeId(nextRecipeId++);
        recipe4.setName("Sushi");
        recipe4.setCuisine("Japanese");
        recipe4.setType("Appetizer");

        List<Photo> photos4 = new ArrayList<>();
        Photo photo7 = new Photo();
        photo7.setPhotoId(1);
        photo7.setPhotoName("Sushi Photo 1");
        photo7.setPhotoPath("sushi-photo-1.jpg");
        photos4.add(photo7);

        Photo photo8 = new Photo();
        photo8.setPhotoId(2);
        photo8.setPhotoName("Sushi Photo 2");
        photo8.setPhotoPath("sushi-photo-2.jpg");
        photos4.add(photo8);

        recipe4.setPhotos(photos4);

        recipeDatabase.add(recipe4);

        Recipe recipe5 = new Recipe();
        recipe5.setRecipeId(nextRecipeId++);
        recipe5.setName("Burger");
        recipe5.setCuisine("American");
        recipe5.setType("Main Dish");

        List<Photo> photos5 = new ArrayList<>();
        Photo photo9 = new Photo();
        photo9.setPhotoId(1);
        photo9.setPhotoName("Burger Photo 1");
        photo9.setPhotoPath("burger-photo-1.jpg");
        photos5.add(photo9);

        Photo photo10 = new Photo();
        photo10.setPhotoId(2);
        photo10.setPhotoName("Burger Photo 2");
        photo10.setPhotoPath("burger-photo-2.jpg");
        photos5.add(photo10);

        recipe5.setPhotos(photos5);

        recipeDatabase.add(recipe5);

        Recipe recipe6 = new Recipe();
        recipe6.setRecipeId(nextRecipeId++);
        recipe6.setName("Chicken Curry");
        recipe6.setCuisine("Indian");
        recipe6.setType("Main Dish");

        List<Photo> photos6 = new ArrayList<>();
        Photo photo11 = new Photo();
        photo11.setPhotoId(1);
        photo11.setPhotoName("Chicken Curry Photo 1");
        photo11.setPhotoPath("chicken-curry-photo-1.jpg");
        photos6.add(photo11);

        Photo photo12 = new Photo();
        photo12.setPhotoId(2);
        photo12.setPhotoName("Chicken Curry Photo 2");
        photo12.setPhotoPath("chicken-curry-photo-2.jpg");
        photos6.add(photo12);

        recipe6.setPhotos(photos6);

        recipeDatabase.add(recipe6);

    }


}
