package ru.catjiara.pp231crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.catjiara.pp231crud.dao.UserDao;
import ru.catjiara.pp231crud.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String getUser(@RequestParam(name="id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("users", userDao.index());
            return "/users/index";
        }
        model.addAttribute("user", userDao.getUser(id));
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
            userDao.save(user);
        } else {
            if (delete) {
                userDao.delete(id);
            } else {
                userDao.update(id, user);
            }
        }
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userDao.getUser(id));
        return "users/edit";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(name="id") int id) {
        model.addAttribute("user", userDao.getUser(id));
        return "users/delete";
    }

//    @PostMapping
//    public String update(Model model, @RequestParam(name="id") int id,
//                         @ModelAttribute("user") User user) {
//        userDao.update(id, user);
//        return "redirect/users";
//    }
}
