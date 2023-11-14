package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.entity.Cuisine;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.Collection;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final IRecipeCollectionService collectionService;
    private final IRememberMeTokenService rememberMeTokenService;
    private final IUserService userService;

    @Autowired
    public HomeController(IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService, IIngredientService ingredientService, IRecipeCollectionService collectionService, IRememberMeTokenService rememberMeTokenService, IUserService userService) {
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
        this.ingredientService = ingredientService;
        this.collectionService = collectionService;
        this.rememberMeTokenService = rememberMeTokenService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model, HttpSession session, HttpServletRequest request) {
        rememberMeTokenService.checkRememberMeToken(request,session);
        model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session, HttpServletRequest request) {
        rememberMeTokenService.checkRememberMeToken(request,session);
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
        List<Cuisine> cuisineCategories = cuisineCategoryService.getAllCuisineCategories();
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
            Model model,
            HttpSession session) {

        if (session.getAttribute("loggedInUserName") != null) {
            model.addAttribute("difficulties", Arrays.asList(Recipe.Difficulty.values()));
            model.addAttribute("types", Arrays.asList(Recipe.RecipeType.values()));
            model.addAttribute("ingredients", ingredientService.getAllIngredients());
            model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
            model.addAttribute("userCollections", collectionService.getRecipeCollectionsByUserId((int) session.getAttribute("loggedInUserId")));

            if (cuisineIds != null) {
                List<Recipe> filteredRecipes = recipeService.filterAndSortRecipes(cuisineIds, types, difficulties, ingredientIds, keyword, ascendingOrder);
                model.addAttribute("filteredRecipes", filteredRecipes);
            }
            return "filterRecipe";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addRecipeToCuisineCollection")
    public String addRecipeToCuisineCategory(@RequestParam("recipeId") int recipeId, @RequestParam("cuisineCategoryId") int cuisineCategoryId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        Collection collection = collectionService.getRecipeCollectionById(cuisineCategoryId);
        collectionService.addRecipeToCollection(collection, recipe);
        return "redirect:/filterRecipe";
    }

    @PostMapping("/createCuisineCollection")
    public String createCuisineCategory(@RequestParam("name") String name, HttpSession session) {
        Collection collection = new Collection();
        collection.setName(name);
        collection.setUserId((int) session.getAttribute("loggedInUserId"));
        collectionService.createRecipeCollection(collection);
        return "redirect:/filterRecipe";
    }
}
