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
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // LIST USERS
    @GetMapping("")
    public String index(Model model) {
        System.out.println("Users: " + userService.getAll().size());
        model.addAttribute("users", userService.getAll());
        return "admin/users"; // templates/admin/users.html
    }

    // ADD USER FORM
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-add"; // recommended: keep admin templates grouped
    }

    // EDIT USER FORM
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        User user = userService.get(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "admin/user-edit";
    }

    // SAVE USER (CREATE OR UPDATE)
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        boolean isNew = (user.getId() == null);
        userService.save(user);

        return isNew
                ? "redirect:/users"
                : "redirect:/users/detail/" + user.getId();
    }

    // USER DETAIL PAGE
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        User user = userService.get(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "admin/user-detail";
    }

    // DELETE USER
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        User user = userService.get(id);
        if (user != null) {
            userService.delete(id);
        }
        return "redirect:/users";
    }
}
