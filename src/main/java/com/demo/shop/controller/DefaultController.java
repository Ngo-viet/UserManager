package com.demo.shop.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DefaultController {
    @GetMapping("/login")
    public String loginIndex(){
        return "login";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutPage(Model model){
        return "login";
    }
}
