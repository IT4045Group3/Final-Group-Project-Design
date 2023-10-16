package com.groupthree.culinarycompanion;

import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.service.IUserService;
import jakarta.servlet.http.HttpSession;
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
    public String showLoginForm() {return "login";}

    @GetMapping("/register")
    public String showRegisterForm() {
        return "login";
    }

    @GetMapping("/about")
    public String aboutPage() { return "about"; }

    @GetMapping("/contact")
    public String contactPage() { return "contact"; }

    @GetMapping("/userProfile")
    public String userProfile(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUserName") != null) {
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

}
