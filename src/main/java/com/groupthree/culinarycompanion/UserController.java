package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.entity.CuisineCategory;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final IUserService userService;
    private final IRecipeService recipeService;
    private final ICuisineCategoryService cuisineCategoryService;

    @Autowired
    public UserController(IUserService userService, IRecipeService recipeService, ICuisineCategoryService cuisineCategoryService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.cuisineCategoryService = cuisineCategoryService;
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
            int loggedInUserName = (int) session.getAttribute("loggedInUserId");
            List<Recipe> myRecipes = recipeService.getRecipesByUserId(loggedInUserName);
            model.addAttribute("myRecipes", myRecipes);
            return "userProfile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logOut")
    public String logout(HttpSession session) {

        session.removeAttribute("loggedInUserName");
        session.removeAttribute("loginSuccessful");
        session.removeAttribute("loggedInUserId");

        return ("redirect:/login");
    }

    @PostMapping("/login")
    public String processLogin(Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {

        if (userService.isValidLogin(email, password)) {
            session.setAttribute("loginSuccessful", "Successful login as " + userService.findUserByEmail(email).getUsername().trim());
            session.setAttribute("loggedInUserName", userService.findUserByEmail(email).getUsername());
            session.setAttribute("loggedInUserId", userService.findUserByEmail(email).getUserId());

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            session.removeAttribute("loginSuccessful");
                        }
                    },
                    1000
            );

            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String processRegistration(Model model, HttpSession session, @RequestParam("email") String email, User user) {

        if (userService.findUserByEmail(email) != null) {
            model.addAttribute("registrationFailure", true);
            model.addAttribute("registerError", "Email already in use");
            return "login";
        }

        session.setAttribute("registerSuccessful", "Register successful");
        model.addAttribute("registrationFailure", false);
        userService.createUser(user);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        session.removeAttribute("registerSuccessful");
                    }
                },
                1000
        );

        return "redirect:/login";
    }

    @PostMapping("/addRecipe")
    public String addRecipe(Recipe newRecipe, @RequestParam("recipeFile") MultipartFile addedPhoto,
                            HttpSession session) throws FileNotFoundException {

        int loggedInUserId = (int) session.getAttribute("loggedInUserId");
        User currentUser = userService.findUserById(loggedInUserId);
        newRecipe.setUser(currentUser);
        Recipe addedRecipe = recipeService.createRecipe(newRecipe);

        String imagePath = userService.saveImage(addedPhoto);
        recipeService.addPhotoInRecipe(addedRecipe.getRecipeId(),imagePath,addedPhoto.getName());
        return "redirect:/home";
    }


    @PostMapping("/addCuisineCategory")
    public String addCuisineCategory(@RequestParam("cuisineFile") MultipartFile addedPhoto,
                                     CuisineCategory newCategory) {

        CuisineCategory addedCategory = cuisineCategoryService.addCuisineCategory(newCategory);

        String imagePath = userService.saveImage(addedPhoto);
        cuisineCategoryService.addPhotoInCategory(addedCategory.getId(),imagePath,addedPhoto.getName());

        return "redirect:/home";
    }

}
