package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cuisineCategories", recipeService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("cuisineCategories", recipeService.getAllCuisineCategories());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

}
