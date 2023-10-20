package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import com.groupthree.culinarycompanion.model.Recipe;
import com.groupthree.culinarycompanion.service.IRecipeService;
import com.groupthree.culinarycompanion.service.RecipeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Recipe> findAllRecipes() {
        return recipeDatabase;
    }

    @PostConstruct
    public void initDefaultRecipes() {

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(nextRecipeId++);
        recipe1.setName("Pakora");

        CuisineCategory cuisineCategory1 = new CuisineCategory();
        cuisineCategory1.setId(1);
        cuisineCategory1.setName("Indian");
        recipe1.setCuisine(cuisineCategory1);

        recipe1.setType("Main Dish");

        List<Photo> photos1 = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setPhotoId(1);
        photo1.setPhotoName("Pakora Photo 1");
        photo1.setPhotoPath("/upload/pakora-photo-1.jpg");
        photos1.add(photo1);

        recipe1.setPhotos(photos1);

        recipeDatabase.add(recipe1);


        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(nextRecipeId++);
        recipe2.setName("Spaghetti Carbonara");

        CuisineCategory cuisineCategory2 = new CuisineCategory();
        cuisineCategory2.setId(1);
        cuisineCategory2.setName("Italian");
        recipe2.setCuisine(cuisineCategory2);

        recipe2.setType("Main Dish");

        List<Photo> photos2 = new ArrayList<>();
        Photo photo3 = new Photo();
        photo3.setPhotoId(1);
        photo3.setPhotoName("Spaghetti Carbonara Photo 1");
        photo3.setPhotoPath("/upload/spaghetti-carbonara-photo-1.jpg");
        photos2.add(photo3);

        recipe2.setPhotos(photos2);

        recipeDatabase.add(recipe2);


        Recipe recipe3 = new Recipe();
        recipe3.setRecipeId(nextRecipeId++);
        recipe3.setName("Jiaozi");

        CuisineCategory cuisineCategory3 = new CuisineCategory();
        cuisineCategory3.setId(1);
        cuisineCategory3.setName("Chinese");
        recipe3.setCuisine(cuisineCategory3);

        recipe3.setType("Main Dish");

        List<Photo> photos3 = new ArrayList<>();
        Photo photo5 = new Photo();
        photo5.setPhotoId(1);
        photo5.setPhotoName("Jiaozi Photo 1");
        photo5.setPhotoPath("/upload/jiaozi-photo-1.jpg");
        photos3.add(photo5);

        recipe3.setPhotos(photos3);

        recipeDatabase.add(recipe3);

        Recipe recipe4 = new Recipe();
        recipe4.setRecipeId(nextRecipeId++);
        recipe4.setName("Sushi");

        CuisineCategory cuisineCategory4 = new CuisineCategory();
        cuisineCategory4.setId(1);
        cuisineCategory4.setName("Japanese");
        recipe4.setCuisine(cuisineCategory4);
        recipe4.setType("Appetizer");

        List<Photo> photos4 = new ArrayList<>();
        Photo photo7 = new Photo();
        photo7.setPhotoId(1);
        photo7.setPhotoName("Sushi Photo 1");
        photo7.setPhotoPath("/upload/sushi-photo-1.jpg");
        photos4.add(photo7);

        recipe4.setPhotos(photos4);

        recipeDatabase.add(recipe4);

        Recipe recipe5 = new Recipe();
        recipe5.setRecipeId(nextRecipeId++);
        recipe5.setName("Burger");

        CuisineCategory cuisineCategory5 = new CuisineCategory();
        cuisineCategory5.setId(1);
        cuisineCategory5.setName("American");
        recipe5.setCuisine(cuisineCategory5);
        recipe5.setType("Main Dish");

        List<Photo> photos5 = new ArrayList<>();
        Photo photo9 = new Photo();
        photo9.setPhotoId(1);
        photo9.setPhotoName("Burger Photo 1");
        photo9.setPhotoPath("/upload/burger-photo-1.jpg");
        photos5.add(photo9);

        recipe5.setPhotos(photos5);

        recipeDatabase.add(recipe5);

        Recipe recipe6 = new Recipe();
        recipe6.setRecipeId(nextRecipeId++);
        recipe6.setName("Chicken Curry");
        CuisineCategory cuisineCategory6 = new CuisineCategory();
        cuisineCategory6.setId(1);
        cuisineCategory6.setName("Indian");
        recipe6.setCuisine(cuisineCategory6);
        recipe6.setType("Main Dish");

        List<Photo> photos6 = new ArrayList<>();
        Photo photo11 = new Photo();
        photo11.setPhotoId(1);
        photo11.setPhotoName("Chicken Curry Photo 1");
        photo11.setPhotoPath("/upload/chicken-curry-photo-1.jpg");
        photos6.add(photo11);

        recipe6.setPhotos(photos6);

        recipeDatabase.add(recipe6);

    }
}
