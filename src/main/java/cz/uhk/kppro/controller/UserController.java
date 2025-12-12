package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    // LIST USERS
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/users";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/add-user";
    }

    // EDIT USER FORM
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        User user = userService.get(id);

        if (user == null) {
            return "redirect:/users";
        }

        // Ensure role object exists for binding
        if (user.getRole() == null) {
            user.setRole(new Role());
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-edit";
    }

    // SAVE USER (UPDATE)
    @PostMapping("/save")
    public String updateUser(
            @ModelAttribute User formUser,
            @RequestParam Long roleId,
            @RequestParam(required = false) String newPassword) {

        userService.updateUser(formUser, newPassword, roleId);

        return "redirect:/users/detail/" + formUser.getId();
    }



    @PostMapping("/create")
    public String create(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Long roleId) {

        User user = new User();
        user.setUsername(username);

        userService.createUser(user, password, roleId);

        return "redirect:/users";
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
        userService.delete(id);
        return "redirect:/users";
    }
}
