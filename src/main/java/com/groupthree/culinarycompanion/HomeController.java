package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.dao.RecipeDAOStub;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Recipe;
import com.groupthree.culinarycompanion.service.CuisineCategoryService;
import com.groupthree.culinarycompanion.service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CuisineCategoryService cuisineCategoryService;

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
        List<RecipeDTO> searchResults = recipeService.findRecipesByNameContaining(keyword);
        model.addAttribute("searchResults", searchResults);
        return "home";
    }

}
