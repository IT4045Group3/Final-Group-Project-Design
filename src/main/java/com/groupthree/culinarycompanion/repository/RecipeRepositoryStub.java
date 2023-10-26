package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.CuisineCategory;
import com.groupthree.culinarycompanion.entity.Instruction;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.entity.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepositoryStub implements RecipeRepository {
    private List<Recipe> recipeDatabase = new ArrayList<>();
    private int nextRecipeId = 1;
    private int nextInstructionId = 1;
    private int nextPhotoId = 1;

    private CuisineCategoryRepository cuisineCategoryDao;

    public RecipeRepositoryStub(CuisineCategoryRepository cuisineCategoryDao) {
        this.cuisineCategoryDao = cuisineCategoryDao;
    }

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
    public Recipe createRecipe(Recipe recipe) {
        recipe.setRecipeId(nextRecipeId);
        nextRecipeId++;

        if (recipe.getCuisine() != null) {
            CuisineCategory cuisineCategory = cuisineCategoryDao.findCuisineById(recipe.getCuisine().getId());
            recipe.setCuisine(cuisineCategory);
        }
        recipeDatabase.add(recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(Recipe updatedRecipe) {
        // Find the existing recipe in the database
        Recipe existingRecipe = findRecipeById(updatedRecipe.getRecipeId());

        if (existingRecipe != null) {
            // Check and update each field if it has changed

            // Check and update the name
            if (updatedRecipe.getName() != null) {
                existingRecipe.setName(updatedRecipe.getName());
            }

            // Check and update the cuisine
            if (updatedRecipe.getCuisine() != null) {
                CuisineCategory cuisineCategory = cuisineCategoryDao.findCuisineById(updatedRecipe.getCuisine().getId());
                existingRecipe.setCuisine(cuisineCategory);
            }

            // Check and update the type
            if (updatedRecipe.getType() != null) {
                existingRecipe.setType(updatedRecipe.getType());
            }

            // Check and update the difficulty
            if (updatedRecipe.getDifficulty() != null) {
                existingRecipe.setDifficulty(updatedRecipe.getDifficulty());
            }

            // Check and update ingredients if it's not an empty list
            if (updatedRecipe.getIngredients() != null && !updatedRecipe.getIngredients().isEmpty()) {
                existingRecipe.setIngredients(updatedRecipe.getIngredients());
            }

            // Check and update instructions if it's not an empty list
            if (updatedRecipe.getInstructions() != null && !updatedRecipe.getInstructions().isEmpty()) {
                existingRecipe.setInstructions(updatedRecipe.getInstructions());
            }

            // Check and update photos if it's not an empty list
            if (updatedRecipe.getPhotos() != null && !updatedRecipe.getPhotos().isEmpty()) {
                existingRecipe.setPhotos(updatedRecipe.getPhotos());
            }

            // Update the user if it has changed
            if (updatedRecipe.getUser() != null) {
                existingRecipe.setUser(updatedRecipe.getUser());
            }

            // Replace the existing recipe with the updated one
            for (int i = 0; i < recipeDatabase.size(); i++) {
                if (recipeDatabase.get(i).getRecipeId() == existingRecipe.getRecipeId()) {
                    recipeDatabase.set(i, existingRecipe);
                    break;
                }
            }
        }
        return existingRecipe;
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
            nextRecipeId--;

        }
    }
    @Override
    public List<Recipe> findAllRecipes() {
        return recipeDatabase;
    }

    @Override
    public List<Recipe> findByNameContaining(String keyword) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    @Override
    public List<Recipe> getRecipesByCategory(int categoryId) {
        List<Recipe> recipesByCategory = new ArrayList<>();
        for (Recipe recipe : recipeDatabase) {
            if (recipe.getCuisine().getId() == categoryId) {
                recipesByCategory.add(recipe);
            }
        }
        return recipesByCategory;
    }

    @Override
    public Instruction addInstructionToRecipe(int recipeId, Instruction instruction) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            List<Instruction> instructions = recipe.getInstructions();
            instruction.setInstructionId(nextInstructionId);
            nextInstructionId++;
            instructions.add(instruction);
            recipe.setInstructions(instructions);
        }
        return instruction;

    }

    @Override
    public void addPhotoInInstruction(int recipeId, int instructionId, String imagePath) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            List<Instruction> instructions = recipe.getInstructions();
            for (Instruction instruction : instructions) {
                if (instruction.getInstructionId() == instructionId) {
                    Photo newPhoto = new Photo();
                    newPhoto.setPhotoId(nextPhotoId);
                    nextPhotoId++;
                    newPhoto.setPhotoName("New Photo");
                    newPhoto.setPhotoPath(imagePath);

                    List<Photo> photos = instruction.getPhotos();
                    photos.add(newPhoto);

                    instruction.setPhotos(photos);
                }
            }

            recipe.setInstructions(instructions);
        }
    }


    @Override
    public void removeInstructionFromRecipe(int recipeId, int instructionId) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            List<Instruction> instructions = recipe.getInstructions();
            instructions.removeIf(instruction -> instruction.getInstructionId() == instructionId);
            recipe.setInstructions(instructions);
        }
    }

    @Override
    public void updateInstructionToRecipe(int recipeId, int instructionId, Instruction updatedInstruction) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            List<Instruction> instructions = recipe.getInstructions();
            for (Instruction instruction : instructions) {
                if (instruction.getInstructionId() == instructionId) {

                    if (!instruction.getDescription().equals(updatedInstruction.getDescription())) {
                        instruction.setDescription(updatedInstruction.getDescription());
                    }
                    if (!instruction.getVideoURL().equals(updatedInstruction.getVideoURL())) {
                        instruction.setVideoURL(updatedInstruction.getVideoURL());
                    }
                    if (instruction.getStepNumber() != updatedInstruction.getStepNumber()) {
                        instruction.setStepNumber(updatedInstruction.getStepNumber());
                    }

                    recipe.setInstructions(instructions);
                }
            }
        }
    }

    @Override
    public void deletePhotoToInstruction(int recipeId, int instructionId, int photoId) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            List<Instruction> instructions = recipe.getInstructions();
            for (Instruction instruction : instructions) {
                if (instruction.getInstructionId() == instructionId) {
                    List<Photo> photos = instruction.getPhotos();
                    photos.removeIf(photo -> photo.getPhotoId() == photoId);
                    instruction.setPhotos(photos);
                }
            }
            recipe.setInstructions(instructions);
        }
    }


    @PostConstruct
    public void initDefaultRecipes() {

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(nextRecipeId++);
        recipe1.setName("Pakora");

        CuisineCategory cuisineCategory1 = new CuisineCategory();
        cuisineCategory1.setId(3);
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
        cuisineCategory2.setId(3);
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
        cuisineCategory4.setId(4);
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
        cuisineCategory5.setId(2);
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
        cuisineCategory6.setId(3);
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
