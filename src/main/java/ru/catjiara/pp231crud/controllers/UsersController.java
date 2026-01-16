package ru.catjiara.pp231crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public String index(Model model) {
        return null;
    }
    @GetMapping
    public String getUser(@RequestParam(name="id") int id, Model model) {
        return null;
    }

}
