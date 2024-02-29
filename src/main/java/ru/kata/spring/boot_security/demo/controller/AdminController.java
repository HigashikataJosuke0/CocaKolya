package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceFind;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserServiceFind userServiceFind;
    @Autowired
    public AdminController(UserService userService, UserServiceFind userServiceFind) {
        this.userService = userService;
        this.userServiceFind = userServiceFind;
    }

    @RequestMapping(value = "/allusers")
      public String getAllUsers(ModelMap model) {
        model.addAttribute("allusers", userService.getAll());
        return "allusers";
    }

    @RequestMapping("/addnewuser")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "/addnewuser";
    }

    @RequestMapping("saveUser")
    public String saveUser(ModelMap modelMap, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(user);
            return "redirect:/allusers";
        }

        userService.save(user);

        return "redirect:/allusers";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usrId") long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/addnewuser";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") long id) {
        userService.removeById(id);
        return "redirect:/allusers";
    }
}
