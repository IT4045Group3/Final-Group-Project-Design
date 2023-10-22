package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.service.ICuisineCategoryService;
import com.groupthree.culinarycompanion.service.IRecipeService;
import com.groupthree.culinarycompanion.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.groupthree.culinarycompanion.model.Recipe;
import com.groupthree.culinarycompanion.service.RecipeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {
    private final IUserService userService;
    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;

    @Autowired
    public RecipeController(IUserService userService, IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
    }

    @GetMapping("/updateRecipe/{recipeId}")
    public String editRecipe(@PathVariable int recipeId, Model model) {
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }

    @PostMapping("/updateRecipe/{recipeId}")
    public String updateRecipe(HttpSession session, @PathVariable int recipeId, @RequestParam("photo") MultipartFile photo, RecipeDTO updatedRecipe) {

        if (photo != null && !photo.isEmpty()) {

            String imagePath = userService.saveImage(photo);
            List<PhotoDTO> photos = new ArrayList<>();
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPhotoName(updatedRecipe.getName());
            photoDTO.setPhotoPath(imagePath);
            photos.add(photoDTO);
            updatedRecipe.setPhotos(photos);

        }
        else {
            RecipeDTO existingRecipe = recipeService.findRecipeById(recipeId);
            updatedRecipe.setPhotos(existingRecipe.getPhotos());
        }

        List<InstructionDTO> instructions = updatedRecipe.getInstructions();

        if (instructions != null) {
            for (InstructionDTO instruction : instructions) {
                if (instruction.getInstructionId() == 0) {
                    recipeService.addInstructionToRecipe(recipeId, instruction);
                } else {
                    recipeService.updateInstructionInRecipe(recipeId, instruction.getInstructionId(), instruction);
                }
            }
        }


        int loggedInUserId = (int) session.getAttribute("loggedInUserId");
        UserDTO currentUser = userService.findUserById(loggedInUserId);
        currentUser.getRecipes().removeIf(recipe -> recipe.getRecipeId() == recipeId);
        currentUser.getRecipes().add(recipeService.updateRecipe(recipeId, updatedRecipe));
        userService.updateUser(currentUser.getUserId(), currentUser);

        return "redirect:/userProfile";
    }

    @GetMapping("/deleteRecipe/{recipeId}")
    public String deleteRecipe(HttpSession session, @PathVariable int recipeId) {

        int loggedInUserId = (int) session.getAttribute("loggedInUserId");
        UserDTO currentUser = userService.findUserById(loggedInUserId);
        currentUser.getRecipes().removeIf(recipe -> recipe.getRecipeId() == recipeId);
        recipeService.deleteRecipe(recipeId);
        userService.updateUser(currentUser.getUserId(),currentUser);

        return "redirect:/userProfile";
    }

}
