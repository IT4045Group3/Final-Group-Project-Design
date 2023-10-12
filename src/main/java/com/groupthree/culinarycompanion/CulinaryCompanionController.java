package com.groupthree.culinarycompanion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CulinaryCompanionController {


    @GetMapping("/")
    public String homePage() {
        return "home";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
}
