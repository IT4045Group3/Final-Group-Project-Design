package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
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

    @GetMapping("/editRecipe/{recipeId}")
    public String editRecipe(@PathVariable int recipeId, Model model) {
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }

    @PostMapping("/updateRecipe/{recipeId}")
    public String updateRecipe(HttpSession session, @PathVariable int recipeId, RecipeDTO updatedRecipe) {

        int loggedInUserId = (int) session.getAttribute("loggedInUserId");
        UserDTO currentUser = userService.findUserById(loggedInUserId);

        currentUser.getRecipes().removeIf(recipe -> recipe.getRecipeId() == recipeId);
        currentUser.getRecipes().add(recipeService.updateRecipe(recipeId, updatedRecipe));
        userService.updateUser(currentUser.getUserId(),currentUser);

        return "redirect:/userProfile";
    }

    @GetMapping("/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable int recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/userProfile";
    }
}
