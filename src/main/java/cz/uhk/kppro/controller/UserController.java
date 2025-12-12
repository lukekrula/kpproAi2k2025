package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.User;

import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @Autowired
    public void setDriverService(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", userService.getAll());
        return "users";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        User user = userService.get(id);
        if (user == null) {
            return "redirect:/users/";
        }
        model.addAttribute("user", user);
        return "edit";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        boolean test = user.getId() == 0;
        userService.save(user);
        return test ? "redirect:/users/" :
                "redirect:/users/detail/" + String.valueOf(user.getId());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        User user = userService.get(Long.valueOf(id));
        if(user != null) {
            model.addAttribute("user", user);
            return "detail";
        }else{
            return "redirect:/users/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        User user = userService.get(Long.valueOf(id));
        if(user != null) {
            userService.delete(Long.valueOf(id));
            return "redirect:/users/";
        }else{
            return "redirect:/users/";
        }
    }



}
