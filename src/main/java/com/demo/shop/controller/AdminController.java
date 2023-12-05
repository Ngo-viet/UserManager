package com.demo.shop.controller;

import com.demo.shop.entities.Role;
import com.demo.shop.entities.User;
import com.demo.shop.repository.RoleRepository;
import com.demo.shop.repository.UserRepository;
import com.demo.shop.service.RoleService;
import com.demo.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@SpringBootApplication
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/index")
    public String adminIndex(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "index_admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/adduser")
    public String addUser(Model model ){

        User user = new User();
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", user);

        model.addAttribute("roles", roles );
        return "common/admin/add_user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/saveuser")
    public String addUserByAdmin(@Valid User user, @Valid Role role, BindingResult result, Model model,  @RequestParam("roleIds") List<Long> roleIds){
        String pw = user.getPassword();
        String encrypt = passwordEncoder.encode(pw);
        user.setPassword(encrypt);
        if(result.hasErrors()){
            return "common/admin/add_user";
        }
        Set<Role> roles = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);

        return "redirect:/admin/index";
    }

    @GetMapping("/admin/edituser/{id}")
    public String editUser(@PathVariable("id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Khong hop le ID:" + id));
        model.addAttribute("user", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles );
        return "common/admin/edit_user";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user, @Valid Role role,
                             BindingResult result, Model model, @RequestParam("roleIds") List<Long> roleIds) {
        if (result.hasErrors()) {
            user.setId(id);
            return "common/admin/edit_user";
        }
        user.setUsername(user.getUsername());

        user.setUser_email(user.getUser_email());
        user.setUser_address(user.getUser_address());

        Set<Role> roles = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin/index";
    }

    @GetMapping ("/admin/deleteuser/{id}")
    public String deleleUser(@PathVariable("id") long id){


        userService.delete(id);
        return "redirect:/admin/index";
    }

}
