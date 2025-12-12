package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.service.RoleService;
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
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository, RoleService roleService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
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
    // EDIT USER FORM
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        User user = userService.get(id);
        if (user == null) {
            return "redirect:/users";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-edit";
    }


    @PostMapping("/save")
    public String updateUser(
            @ModelAttribute("user") User formUser,
            @RequestParam(required = false) String newPassword,
            Model model) {
        System.out.println(">>> FORM USER = " + formUser);
        System.out.println(">>> FORM USER ID = " + formUser.getId());
        System.out.println(">>> FORM USER ROLE = " + (formUser.getRole() != null ? formUser.getRole().getId() : "null"));

        // Validate: user must exist
        User existing = userService.get(formUser.getId());
        if (existing == null) {
            return "redirect:/users";
        }

        // Validate: role must exist
        if (formUser.getRole() == null || formUser.getRole().getId() == null) {
            model.addAttribute("error", "Role is required.");
            model.addAttribute("roles", roleRepository.findAll());

            if (existing.getRole() == null) {
                existing.setRole(new Role());
            }

            model.addAttribute("user", existing);
            return "admin/user-edit";
        }



        // Update user using service layer
        userService.updateUser(
                formUser,
                newPassword,
                formUser.getRole().getId()
        );

        //  Redirect to detail page
        return "redirect:/users/detail/" + formUser.getId();
    }




    @PostMapping("/create")
    public String create(@ModelAttribute User user,
                       @RequestParam(required = false) String newPassword,
                         @RequestParam(required = false) long roleId) {

        userService.updateUser(user, newPassword, roleId);

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
        User user = userService.get(id);
        if (user != null) {
            userService.delete(id);
        }
        return "redirect:/users";
    }
}
