package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CulinaryCompanionController {

    // Handle requests for the home page
    private final IUserService userService;

    public CulinaryCompanionController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home"; // Returns the view name (home.html)
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // This will return the "home.html" page
    }

    // Handle requests for the login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // This will return the "login.html" page
    }

    @PostMapping("/login")
    public String processLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        // You can use your UserService to check if the login is successful
        if (userService.isValidLogin(email, password)) {
            // Redirect to home.html if login is successful
            return "home";
        } else {
            // Add an error message to the model and return to the login page
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
