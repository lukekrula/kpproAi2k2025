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
            @RequestParam(required = false) String newPassword,
            Model model) {

        System.out.println(">>> FORM USER ID = " + formUser.getId());
        System.out.println(">>> FORM USER ROLE = " +
                (formUser.getRole() != null ? formUser.getRole().getId() : "null"));

        User existing = userService.get(formUser.getId());
        if (existing == null) {
            return "redirect:/users";
        }

        // If role missing → redisplay form
        if (formUser.getRole() == null || formUser.getRole().getId() == null) {
            model.addAttribute("error", "Role is required.");
            model.addAttribute("roles", roleRepository.findAll());

            // Ensure role object exists
            if (existing.getRole() == null) {
                existing.setRole(new Role());
            }

            model.addAttribute("user", existing);
            return "admin/user-edit";
        }

        // Update user
        userService.updateUser(
                formUser,
                newPassword,
                formUser.getRole().getId()
        );

        return "redirect:/users/detail/" + formUser.getId();
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
