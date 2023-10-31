package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.entity.Instruction;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {
    private final IUserService userService;
    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;
    private final IInstructionService instructionService;

    @Autowired
    public RecipeController(IUserService userService, IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService, IInstructionService instructionService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
        this.instructionService = instructionService;
    }

    @GetMapping("/updateRecipe/{recipeId}")
    public String editRecipe(@PathVariable int recipeId, Model model) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }

    @PostMapping("/updateRecipe/{recipeId}")
    public String updateRecipe(@PathVariable int recipeId, @RequestParam("photo") MultipartFile updatedPhoto, Recipe updatedRecipe) {

        if (updatedPhoto != null && !updatedPhoto.isEmpty()) {

            String imagePath = userService.saveImage(updatedPhoto);
            recipeService.updatePhotoInRecipe(recipeId,imagePath,updatedPhoto.getName());

        }
        else {

            Recipe existingRecipe = recipeService.findRecipeById(recipeId);
            updatedRecipe.setPhotos(existingRecipe.getPhotos());
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
}
