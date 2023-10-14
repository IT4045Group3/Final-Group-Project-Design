package com.groupthree.culinarycompanion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CulinaryCompanionController {

    @RequestMapping("/")
    public String homepage(){
        return "home";
    }

}