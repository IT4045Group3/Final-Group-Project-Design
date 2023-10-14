package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {

        if (userService.isValidLogin(email, password)) {
            model.addAttribute("loginSuccessful","Successful login as " + userService.findUserByEmail(email).getUsername().trim());
            return "home";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String processRegistration(Model model, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {

        if (userService.findUserByEmail(email) != null) {
            model.addAttribute("registrationFailure", true);
            model.addAttribute("registerError", "Email already in use");
            return "login";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        model.addAttribute("registerSuccessful", "Register successful");
        model.addAttribute("registrationFailure", false);

        userService.createUser(userDTO);

        return "login";
    }


}
