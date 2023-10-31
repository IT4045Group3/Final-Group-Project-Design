package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.entity.CuisineCategory;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.service.impl.CuisineCategoryServiceImpl;
import com.groupthree.culinarycompanion.service.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    @Autowired
    private CuisineCategoryServiceImpl cuisineCategoryServiceImpl;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryServiceImpl.getAllCuisineCategories());
        model.addAttribute("recipes", recipeServiceImpl.getAllRecipes());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryServiceImpl.getAllCuisineCategories());
        model.addAttribute("recipes", recipeServiceImpl.getAllRecipes());
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryServiceImpl.getAllCuisineCategories());
        model.addAttribute("recipes", recipeServiceImpl.getAllRecipes());
        List<Recipe> searchResults = recipeServiceImpl.findRecipesByNameContaining(keyword);
        model.addAttribute("searchResults", searchResults);
        return "home";
    }

    @GetMapping("/recipes-by-category/{categoryId}")
    public String recipesByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        model.addAttribute("cuisineCategories", cuisineCategoryServiceImpl.getAllCuisineCategories());
        model.addAttribute("recipes", recipeServiceImpl.getAllRecipes());
        List<Recipe> recipes = recipeServiceImpl.getRecipesByCategory(categoryId);
        List<CuisineCategory> cuisineCategories = cuisineCategoryServiceImpl.getAllCuisineCategories();
        model.addAttribute("cuisineCategories", cuisineCategories);
        model.addAttribute("recipes", recipes);
        return "home";
    }

}
