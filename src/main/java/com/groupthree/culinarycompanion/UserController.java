package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.CuisineCategoryDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;
import com.groupthree.culinarycompanion.model.Photo;
import com.groupthree.culinarycompanion.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
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
            return "userProfile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logOut")
    public String logout(HttpSession session) {

        session.removeAttribute("loggedInUserName");
        session.removeAttribute("loginSuccessful");

        return ("redirect:/login");
    }

    @PostMapping("/login")
    public String processLogin(Model model, HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {

        if (userService.isValidLogin(email, password)) {
            session.setAttribute("loginSuccessful", "Successful login as " + userService.findUserByEmail(email).getUsername().trim());
            session.setAttribute("loggedInUserName", userService.findUserByEmail(email).getUsername());

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
    public String processRegistration(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {

        if (userService.findUserByEmail(email) != null) {
            model.addAttribute("registrationFailure", true);
            model.addAttribute("registerError", "Email already in use");
            return "login";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        session.setAttribute("registerSuccessful", "Register successful");
        model.addAttribute("registrationFailure", false);

        userService.createUser(userDTO);

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
    public String addRecipe(@RequestParam("nameRecipe") String name,
                            @RequestParam("cuisine") int cuisineId,
                            @RequestParam("type") String type,
                            @RequestParam("difficulty") String difficulty,
                            @RequestParam("recipeFile") MultipartFile file) throws FileNotFoundException {

        String imagePath = userService.saveImage(file);

        RecipeDTO newRecipe = new RecipeDTO();
        newRecipe.setName(name);

        CuisineCategory cuisineCategory = cuisineCategoryService.getCuisineById(cuisineId);
        newRecipe.setCuisine(cuisineCategory);

        newRecipe.setType(type);
        newRecipe.setDifficulty(difficulty);

        List<PhotoDTO> photos = new ArrayList<>();
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhotoName(name);
        photoDTO.setPhotoPath(imagePath);

        photos.add(photoDTO);
        newRecipe.setPhotos(photos);

        recipeService.createRecipe(newRecipe);

        return "redirect:/home";
    }

    @PostMapping("/addCuisineCategory")
    public String addCuisineCategory(@RequestParam("name") String name,
                                     @RequestParam("cuisineFile") MultipartFile imageFile) {


        String imagePath = userService.saveImage(imageFile);

        CuisineCategoryDTO newCategory = new CuisineCategoryDTO();
        newCategory.setName(name);

        List<PhotoDTO> photos = new ArrayList<>();
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhotoName(name);
        photoDTO.setPhotoPath(imagePath);

        photos.add(photoDTO);
        newCategory.setPhotos(photos);

        cuisineCategoryService.addCuisineCategory(newCategory);

        return "redirect:/home";
    }

}
