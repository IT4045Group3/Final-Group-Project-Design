package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.entity.*;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecipeController {
    private final IUserService userService;
    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;
    private final IInstructionService instructionService;
    private final IIngredientService ingredientService;

    @Autowired
    public RecipeController(IUserService userService, IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService, IInstructionService instructionService, IIngredientService ingredientService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
        this.instructionService = instructionService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/updateRecipe/{recipeId}")
    public String editRecipe(@PathVariable int recipeId, Model model) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("allIngredients", ingredientService.getAllIngredients());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        model.addAttribute("recipe", recipe);
        model.addAttribute("difficulties", Arrays.asList(Recipe.Difficulty.values()));
        model.addAttribute("types", Arrays.asList(Recipe.RecipeType.values()));
        return "editRecipe";
    }

    @PostMapping("/updateRecipe/{recipeId}")
    public String updateRecipe(@PathVariable int recipeId, @RequestParam("photo") MultipartFile updatedPhoto, @ModelAttribute Recipe updatedRecipe) {

        if (updatedPhoto != null && !updatedPhoto.isEmpty()) {

            String imagePath = userService.saveImage(updatedPhoto);
            recipeService.updatePhotoInRecipe(recipeId,imagePath,updatedPhoto.getName());

        }
        recipeService.updateRecipe(recipeId, updatedRecipe);

        return "redirect:/userProfile";
    }

    @GetMapping("/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable int recipeId) {

        recipeService.deleteRecipe(recipeId);
        return "redirect:/userProfile";
    }

    @PostMapping("/addInstruction/{recipeId}")
    public String addInstruction(@PathVariable int recipeId, Instruction newInstruction, @RequestParam("instructionImage") MultipartFile instructionPhoto) {

        String imagePath = userService.saveImage(instructionPhoto);
        Instruction instruction = instructionService.addInstructionToRecipe(recipeId, newInstruction);
        instructionService.addPhotoInInstruction(recipeId, instruction.getInstructionId(), imagePath, instructionPhoto.getName());
        return "redirect:/updateRecipe/" + recipeId;
    }

    @PostMapping("/updateInstruction/{recipeId}/{instructionId}")
    public String updateInstruction(@PathVariable int recipeId, @PathVariable int instructionId, Instruction updatedInstruction) {
        instructionService.updateInstructionInRecipe(recipeId, instructionId, updatedInstruction);
        return "redirect:/updateRecipe/" + recipeId;
    }

    @GetMapping("/deleteInstruction/{recipeId}/{instructionId}")
    public String deleteInstruction(@PathVariable int recipeId, @PathVariable int instructionId) {
        instructionService.removeInstructionFromRecipe(recipeId, instructionId);
        return "redirect:/updateRecipe/" + recipeId;
    }

    @PostMapping("/addPhoto/{recipeId}/{instructionId}")
    public String getAddPhotoForm(@PathVariable int recipeId, @PathVariable int instructionId, @RequestParam("instructionImageEditAdd") MultipartFile instructionImageAdd, Model model) {
        String imagePath = userService.saveImage(instructionImageAdd);
        instructionService.addPhotoInInstruction(recipeId, instructionId, imagePath, instructionImageAdd.getName());
        return "redirect:/updateRecipe/" + recipeId;
    }

    @GetMapping("/deletePhoto/{recipeId}/{instructionId}/{photoId}")
    public String deletePhoto(@PathVariable int recipeId, @PathVariable int instructionId, @PathVariable int photoId) {
        instructionService.deletePhotoToInstruction(recipeId, instructionId, photoId);
        return "redirect:/updateRecipe/" + recipeId;
    }

    @GetMapping("/recipe-details/{recipeId}")
    public String viewRecipeDetails(@PathVariable int recipeId, Model model) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        model.addAttribute("recipeDetail", recipe);
        return "recipe-details";
    }

    @PostMapping("/addIngredients")
    public String addIngredients(@RequestParam("ingredientNames") String  ingredientNames) {

        ingredientService.createIngredientsFromTextarea(ingredientNames);
        return "redirect:/userProfile";
    }
}
