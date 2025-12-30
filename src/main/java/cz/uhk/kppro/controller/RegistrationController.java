package cz.uhk.kppro.controller;

import cz.uhk.kppro.dto.RegistrationDto;
import cz.uhk.kppro.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String processForm(@ModelAttribute("user") RegistrationDto dto) {
        registrationService.register(dto);
        return "redirect:/login?registered";
    }
}
