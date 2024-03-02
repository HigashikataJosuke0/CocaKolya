package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceFind;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserServiceFind userServiceFind;

    @Autowired
    public UserController(UserService userService, UserServiceFind userServiceFind) {
        this.userService = userService;
        this.userServiceFind = userServiceFind;
    }

    @GetMapping
    public String getUserById (Principal principal, Model modelMap){
        User user = userServiceFind.findByUSerName(principal.getName());
        System.out.println(user);
        modelMap.addAttribute("user",user.getUsername()+" "+user
                .getSurname()+" "+user.getSalary());
        return "user";
    }
}
