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
    public String create(@ModelAttribute("user") User user){
        return "users/new";
    }

    @PostMapping("/doCreate")
    public String doCreate(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/delete";
    }

    @PostMapping("/doDelete")
    public String doDelete(@ModelAttribute("user") User user, @RequestParam(name="id", required = true) Integer id) {
            userService.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String update(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }
    @PostMapping("/doUpdate")
    public String doUpdate(@ModelAttribute("user") User user, @RequestParam(name="id", required = true) Integer id) {
        userService.update(id, user);
        return "redirect:/users";
    }




}
