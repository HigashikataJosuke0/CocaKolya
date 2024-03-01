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

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
      public String getAllUsers(ModelMap model) {
        model.addAttribute("allusers", userService.getAll());
        return "allusers";
    }

    @GetMapping("addnewuser")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "/addnewuser";
    }

    @PostMapping("saveUser")
    public String saveUser(ModelMap modelMap, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(user);
            return "redirect:/admin/";
        }

        userService.save(user);

        return "redirect:/admin/";
    }
    @GetMapping("updateInfo")
    public String updateUser(@RequestParam("usrId") long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/addnewuser";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("usrId") long id) {
        userService.removeById(id);
        return "redirect:/admin/";
    }
}
