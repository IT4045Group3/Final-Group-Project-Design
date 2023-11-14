package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.entity.Cuisine;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private final IUserService userService;
    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;
    private final IIngredientService ingredientService;
    private final IRememberMeTokenService rememberMeTokenService;

    @Autowired
    public UserController(IUserService userService, IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService, IIngredientService ingredientService, IRememberMeTokenService rememberMeTokenService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
        this.ingredientService = ingredientService;
        this.rememberMeTokenService = rememberMeTokenService;
    }

    @GetMapping("/login")
    public String showLoginForm() {return "login";}

    @GetMapping("/register")
    public String showRegisterForm() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUserName") != null) {

            model.addAttribute("cuisineCategories", cuisineCategoryService.getAllCuisineCategories());
            model.addAttribute("allIngredients", ingredientService.getAllIngredients());
            model.addAttribute("difficulties", Arrays.asList(Recipe.Difficulty.values()));
            model.addAttribute("types", Arrays.asList(Recipe.RecipeType.values()));
            int loggedInUserName = (int) session.getAttribute("loggedInUserId");
            List<Recipe> myRecipes = recipeService.getRecipesByUserId(loggedInUserName);
            model.addAttribute("myRecipes", myRecipes);
            return "userProfile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logOut")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        rememberMeTokenService.clearSessionAttributes(session);
        rememberMeTokenService.clearRememberMeToken(request, response);
        return ("redirect:/login");
    }

    @PostMapping("/login")
    public String processLogin(Model model,
                               HttpSession session,
                               HttpServletResponse response,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam(name = "remember-me", required = false) boolean rememberMe) {

        if (userService.isValidLogin(email, password)) {
            userService.loginUser(session, response, email);
            if (rememberMe) {
                rememberMeTokenService.rememberUser(response, email);
            }
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String processRegistration(Model model,
                                      HttpSession session,
                                      @RequestParam("email") String email,
                                      User user,
                                      @RequestParam(value = "agree", defaultValue = "false") boolean agree) {

        if (userService.validateRegistration(model, email, agree)) {
            return "login";
        }
        userService.handleSuccessfulRegistration(session, user);
        return "redirect:/login";
    }

    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe newRecipe, @RequestParam("recipeFile") MultipartFile addedPhoto,
                            HttpSession session) throws FileNotFoundException {

        int loggedInUserId = (int) session.getAttribute("loggedInUserId");
        User currentUser = userService.findUserById(loggedInUserId);
        newRecipe.setUser(currentUser);
        Recipe addedRecipe = recipeService.createRecipe(newRecipe);

        if (addedPhoto != null && !addedPhoto.isEmpty()) {
            String imagePath = userService.saveImage(addedPhoto);
            recipeService.addPhotoInRecipe(addedRecipe.getRecipeId(), imagePath, addedPhoto.getName());
        }
        return "redirect:/home";
    }


    @PostMapping("/addCuisineCategory")
    public String addCuisineCategory(@RequestParam("cuisineFile") MultipartFile addedPhoto,
                                     Cuisine newCategory) {

        Cuisine addedCategory = cuisineCategoryService.addCuisineCategory(newCategory);

        if (addedPhoto != null && !addedPhoto.isEmpty()) {
            String imagePath = userService.saveImage(addedPhoto);
            cuisineCategoryService.addPhotoInCategory(addedCategory.getCuisineId(), imagePath, addedPhoto.getName());
        }
        return "redirect:/home";
    }

}
