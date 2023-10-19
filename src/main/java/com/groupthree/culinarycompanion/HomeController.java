package com.groupthree.culinarycompanion;


import com.groupthree.culinarycompanion.service.CuisineCategoryService;
import com.groupthree.culinarycompanion.service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CuisineCategoryService cuisineCategoryService;

    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        session.setAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        session.setAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        session.setAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
        session.setAttribute("recipes", recipeService.getAllRecipes());
        return "home";
    }

}
