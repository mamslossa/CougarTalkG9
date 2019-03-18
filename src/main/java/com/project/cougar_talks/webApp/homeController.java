package com.project.cougar_talks.webApp;

import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

@Controller
public class homeController {

    // inject via application.properties
    @Value("${home.name}")

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("name", "Brian");
        return "home";
    }
}
