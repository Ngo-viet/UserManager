package com.demo.shop.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@SpringBootApplication
public class UserController {


    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String userIndex(){
        return "index_user";
    }
}
