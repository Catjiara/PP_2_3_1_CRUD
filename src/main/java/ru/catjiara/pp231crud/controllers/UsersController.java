package ru.catjiara.pp231crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.catjiara.pp231crud.models.User;
import ru.catjiara.pp231crud.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(@RequestParam(name="id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("users", userService.index());
            return "/users/index";
        }
        model.addAttribute("user", userService.getUser(id));
        return "/users/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "users/new";
    }

    @PostMapping
    public String put(@ModelAttribute("user") User user, @RequestParam(name="id", required = false) Integer id,
                      @RequestParam(name="delete", required = false) boolean delete) {
        if (id == null) {
            userService.save(user);
        } else {
            if (delete) {
                userService.delete(id);
            } else {
                userService.update(id, user);
            }
        }
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/delete";
    }
}
