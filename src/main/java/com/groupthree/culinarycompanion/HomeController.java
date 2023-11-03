package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.entity.CuisineCategory;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.service.*;
import com.groupthree.culinarycompanion.service.impl.CuisineCategoryServiceImpl;
import com.groupthree.culinarycompanion.service.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;
    private final IIngredientService ingredientService;

    @Autowired
    public HomeController(IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService, IIngredientService ingredientService) {
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        List<Recipe> searchResults = recipeService.findRecipesByNameContaining(keyword);
        model.addAttribute("searchResults", searchResults);
        return "home";
    }

    @GetMapping("/recipes-by-category/{categoryId}")
    public String recipesByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        List<Recipe> recipes = recipeService.getRecipesByCategory(categoryId);
        List<CuisineCategory> cuisineCategories = cuisineCategoryService.getAllCuisineCategories();
        model.addAttribute("cuisineCategories", cuisineCategories);
        model.addAttribute("recipes", recipes);
        return "home";
    }

    @RequestMapping("/filterRecipe")
    public String filterRecipes(
            @RequestParam(required = false) List<Integer> cuisineIds,
            @RequestParam(required = false) List<Recipe.RecipeType> types,
            @RequestParam(required = false) List<Recipe.Difficulty> difficulties,
            @RequestParam(required = false) List<Integer> ingredientIds,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "true") boolean ascendingOrder,
            Model model) {

        model.addAttribute("difficulties", Arrays.asList(Recipe.Difficulty.values()));
        model.addAttribute("types", Arrays.asList(Recipe.RecipeType.values()));
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());

        if (cuisineIds != null) {
            List<Recipe> filteredRecipes = recipeService.filterAndSortRecipes(cuisineIds, types, difficulties, ingredientIds, keyword, ascendingOrder);
            model.addAttribute("filteredRecipes", filteredRecipes);
        }

        return "filterRecipe";
    }


}
